from urllib.request import urlopen
from urllib.parse import urlparse
from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.common import exceptions
import re
import random
import time
import datetime

pages= set()
random.seed(datetime.datetime.now)
#웹 드라이버

def getList(startingPage):
    html= urlopen(startingPage)
    bsObj= BeautifulSoup(html,"html.parser")
    newslist = []
    for lists in bsObj.find_all("a",href=re.compile("http:\/\/www\.inven\.co\.kr\/board\/lol\/2778")):
        if lists.attrs['href'] is not None:
            if lists.attrs['href'] not in newslist:
                newslist.append(lists.attrs['href'])            
    return newslist
   
        
def getContents():
    newslist = []
    k=13999
    for a in range(1, 4250000):
        newslist.append("https://gall.dcinside.com/board/view/?id=ib_new1&no=" + str(a) + "&page=2")
    
    for i in newslist:
        if newslist is not None:
            driver = webdriver.Chrome('./chromedriver.exe')
            driver.implicitly_wait(10)
            k= k+1
            a=newslist[k]
            print(k)
            del newslist[k]
            print(a)
            driver.get(a)
            try:
                html=driver.page_source
                bsObj=BeautifulSoup(html,"html.parser")
                contents = bsObj.find_all("p", "usertxt ub-word")
                file = open("디씨.txt", 'a' , encoding = 'utf-8')
                for content in contents:
                    file.write(content.text + '\n')
                file.close()
            except:
                print("저장안됨")    
            driver.quit()


getContents()    
        
