#!/usr/bin/env python
# vim: set fileencoding=utf-8 :

from __future__ import unicode_literals

import requests
import sys
import json

API = "https://api.github.com/markdown/raw"

def get_parser():
    pass
    from optparse import OptionParser

    parser = OptionParser()
    parser.add_option('-i','--input', dest='<filename>',
        help='Input markdown file', metavar="FILE"
    )

if __name__ == '__main__':
    try:
        md = sys.argv[1]
    except Exception:
        sys.exit(1)

    assert md.endswith('.md')

    #d = dict()
    #d['text'] = open(md).read()
    #d['mode'] = 'markdown'

    #payload = json.dumps(d)
    
    r = requests.post(API, data=open(md).read(), headers = {'content-type': 'text/plain'})
    result = '<link href="http://yegle.github.com/assets/themes/the-program/css/style.css" rel="stylesheet"></link>\n<div class="entry-content">%s</div>' % (r.text,)
    open("%s.html" % (md[:-3],), 'w').write(result)
