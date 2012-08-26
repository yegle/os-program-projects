#include <jni.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <stdio.h>

JNIEXPORT jint JNICALL Java_Copy__1jniCopy
  (JNIEnv * env, jobject jobj, jstring from, jstring to)
{
    int fd_to, fd_from;
    char buf[4096];
    ssize_t nread;
    int saved_errno;

    const jbyte *from_cstring, *to_cstring;

    // convert jstring to NULL ended C string
    from_cstring = (*env)->GetStringUTFChars(env, from, NULL);
    to_cstring = (*env)->GetStringUTFChars(env, to, NULL);

    fd_from = open(from_cstring, O_RDONLY);
    if (fd_from < 0)
        return -1;

    fd_to = open(to_cstring, O_WRONLY | O_CREAT | O_TRUNC, 0666);
    if (fd_to < 0)
        goto out_error;

    while (nread = read(fd_from, buf, sizeof buf), nread > 0)
    {
        char *out_ptr = buf;
        ssize_t nwritten;

        do {
            nwritten = write(fd_to, out_ptr, nread);

            if (nwritten >= 0)
            {
                nread -= nwritten;
                out_ptr += nwritten;
            }
            else if (errno != EINTR)
            {
                goto out_error;
            }
        } while (nread > 0);
    }

    if (nread == 0)
    {
        if (close(fd_to) < 0)
        {
            fd_to = -1;
            goto out_error;
        }
        close(fd_from);

        /* Success! */
        return 0;
    }

out_error:
    saved_errno = errno;

    close(fd_from);
    if (fd_to >= 0)
        close(fd_to);

    errno = saved_errno;
    printf("errno=%d", errno);
    return -1;
}
