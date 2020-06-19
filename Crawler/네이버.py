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
    for lists in bsObj.find_all("a",href=re.compile("/main/read\.nhn?")):
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
            
            print(len(newslist))
            del newslist[k]
            print(len(newslist))
            a=a
            print(a)
            driver.get(a)
#더보기 계속 클릭하기
            try:
                댓글더보기 = driver.find_element_by_css_selector('.u_cbox_btn_view_comment')
                댓글더보기.click()
                time.sleep(0.1)
            except : 
                pass
            try: 
                while True:
                    더보기 = driver.find_element_by_css_selector('.u_cbox_btn_more')
                    더보기.click()
                    time.sleep(0.1)
            except :
                pass
            
#댓글추출
            try:
                contents = driver.find_elements_by_css_selector('span.u_cbox_contents')            
                file = open("naver.txt", 'a' , encoding = 'utf-8')
                for content in contents:
                    file.write(content.text + '\n')
                file.close()
            except:
                print("저장안됨")
                continue
            driver.quit()
getContents("https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=100")    
        
