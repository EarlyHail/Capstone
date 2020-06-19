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
    for lists in bsObj.find_all("a",href=re.compile("http:\/\/v\.media\.daum\.net\/v\/")):
        if lists.attrs['href'] is not None:
            if lists.attrs['href'] not in newslist:
                newslist.append(lists.attrs['href'])            
    return newslist
   
        
def getContents(startingPage):

        
    newslist= getList(startingPage)
    newslist= list(set(newslist))
    for i in newslist:
        if newslist is not None:
            driver = webdriver.Chrome('./chromedriver.exe')
            driver.implicitly_wait(30)
            k= random.randint(0, len(newslist)-1)
            a=newslist[k]
            del newslist[k]
            print(len(newslist))
            driver.get(a)
            bound =0
#더보기 계속 클릭하기
            try: 
                while bound<=650:
                    driver.find_element_by_css_selector(
                    "#alex-area > div > div > div > div.cmt_box > div.alex_more > a"
                    ).click()
                    bound= bound +1
                    time.sleep(0.8)
            except:
                pass
#댓글추출
        try:
            html=driver.page_source
            bsObj=BeautifulSoup(html,"html.parser")
            contents = bsObj.find_all("p", "desc_txt font_size_17")          
            file = open("2020-04-19-다음.txt", 'a' , encoding = 'utf-8')
            for content in contents:
                file.write(content.text + '\n')
            print("성공")
        except:
            continue
    
        file.close()
        driver.quit()
getContents("https://media.daum.net/ranking/bestreply/")    
        
