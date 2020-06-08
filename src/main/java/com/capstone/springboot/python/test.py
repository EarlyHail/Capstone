# -*- coding: utf-8 -*-
import sys
comment = str(sys.argv[1])
def func(comment):
    print(sys.argv[1])
    if u"욕" in comment :
        print(1)
    else :
        print(0)
func(comment)
