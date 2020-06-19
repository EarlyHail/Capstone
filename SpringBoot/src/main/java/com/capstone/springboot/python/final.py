#!/usr/bin/env python
# coding: utf-8

# In[56]:


import pandas as pd
import sys
import re
import numpy as np
from konlpy.tag import Okt
import tensorflow.compat.v1 as tf
from tensorflow import keras
import csv
import json
from tensorflow.python.keras.preprocessing.sequence import pad_sequences
from tensorflow.python.keras.preprocessing.text import Tokenizer


# In[ ]:





# In[57]:
leng = len(sys.argv)
comment =""
for i in range(1, leng):
    comment = comment + sys.argv[i] + " "
print(comment)
###comment = sys.argv[1]
keyword = pd.read_csv("C:/Users/HojinLee/IdeaProjects/Capstone/src/main/java/com/capstone/springboot/python/keyword.csv")
comment = re.sub("[^가-힣ㄱ-ㅎㅏ-ㅣ\\s]","",comment)
label = 1            
for key in keyword['keyword']:
    if comment.find(key)>=0:
        label= 0

# In[58]:
comment = pd.DataFrame(np.array([comment]))

okt = Okt()


# In[43]:


def preprocessing(review, okt, remove_stopwords, stop_words=[]):
    #함수의 인자는
    #review : 전처리할 텍스트
    #okt : okt객체를 반복적으로 생성하지 않구 미리 만들어 인자로 받음
    #remove_stopword : 불용어를 제거할지 여부 선택
    
    # 정규 표현식을 활용해 한글 문자가 아닌 것들을 모두 제거
    #1.한글 및 공백 제외한 문자 모두 제거
    review_text = re.sub("[^가-힣ㄱ-ㅎㅏ-ㅣ\\s]","",review)
    
    #2. okt 객체를 활용해 형태소 단위로 나눈다.
    word_review = okt.morphs(review_text, stem= True)
    
    if remove_stopwords:
        #불용어 제거 (선택적)
        word_review = [token for token in word_review if not token in stop_words]
        
        
    return word_review


# In[44]:


stop_words= ['은','는','이','가','하','아','것','들','의','있','되','수','보','주','등','한']
clean_train_review = []

for review in comment[0]:
    #비어 있는 데이터에서 멈추지 않도록 문자열인 경우만 진행
    if type(review)==str:
        clean_train_review.append(preprocessing(review,okt,True,stop_words))
    else :
        clean_train_review.append([]) #string이 아니면 비어있는 값 추가


# In[45]:
tokenizer = Tokenizer()


# In[46]:


with open('C:/Users/HojinLee/IdeaProjects/Capstone/src/main/java/com/capstone/springboot/python/wordIndex.json') as json_file:
    word_index = json.load(json_file)
    tokenizer.word_index = word_index


# In[47]:


tokenizer.word_index


# In[48]:


train_sequences = tokenizer.texts_to_sequences(clean_train_review)


# In[49]:


BATCH_SIZE=16
NUM_EPOCHS = 20
#ocab_size = preproconfigs['vocab_size']
embedding_size = 128

vocab_size = len(tokenizer.word_index)+1
MAX_SEQUENCE_LENGHT = 12


# In[50]:


#학습 데이터를 벡터화
train_inputs = pad_sequences(train_sequences, maxlen = MAX_SEQUENCE_LENGHT, padding='post')
#학습 데이터의 라벨
train_labels = np.array([label])
print(train_inputs)

# In[51]:



def model_fn(features, labels, mode, params):
    TRAIN = mode == tf.estimator.ModeKeys.TRAIN
    EVAL = mode == tf.estimator.ModeKeys.EVAL
    PREDICT = mode == tf.estimator.ModeKeys.PREDICT

    #embedding layer선언
    embedding_layer = tf.keras.layers.Embedding(
                    vocab_size+1,
                    embedding_size)(features['x'])
    #embedding layer에 대한 output에 대해 dropout을 취함
    dropout_emb = tf.keras.layers.Dropout(rate = 0.2)(embedding_layer)

    # filter = 정추, 출력 공간의 차원 ( 컨볼루션의 출력 필터 수)
    conv1 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=3,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool1 = tf.keras.layers.GlobalMaxPool1D()(conv1)
        
        
    conv2 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=4,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool2 = tf.keras.layers.GlobalMaxPool1D()(conv2)
    
    
    conv3 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=5,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool3 = tf.keras.layers.GlobalMaxPool1D()(conv3)
    
    conv4 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=6,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool4 = tf.keras.layers.GlobalMaxPool1D()(conv4)
    
    conv5 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=5,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool5 = tf.keras.layers.GlobalMaxPool1D()(conv5)
    conv6 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=7,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool6 = tf.keras.layers.GlobalMaxPool1D()(conv6)
    conv7 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=4,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool7 = tf.keras.layers.GlobalMaxPool1D()(conv7)
    conv8 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=3,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool8 = tf.keras.layers.GlobalMaxPool1D()(conv8)
    conv9 = tf.keras.layers.Conv1D(
           filters=32,
           kernel_size=6,
           padding='same',
           activation=tf.nn.relu)(dropout_emb)
    pool9 = tf.keras.layers.GlobalMaxPool1D()(conv9)
    
    
    
    concat = keras.layers.concatenate([pool1, pool2, pool3, pool4, pool5, pool6, pool7, pool8, pool9]) #모아주기
  
   

    hidden = tf.keras.layers.Dense(units=250, activation=tf.nn.relu)(concat)   
    #u nits = 출력 공간의 양의 정수, 차원.
    # activation = 사용할 활성화 기능,

    dropout_hidden = tf.keras.layers.Dropout(rate=0.2)(hidden, training = TRAIN)
    logits = tf.keras.layers.Dense(units=1)(dropout_hidden)

    if labels is not None:
        labels = tf.reshape(labels, [-1, 1])
        
    if TRAIN:
        global_step = tf.train.get_global_step()
        loss = tf.losses.sigmoid_cross_entropy(labels, logits)
        train_op = tf.train.AdamOptimizer(0.001).minimize(loss, global_step)

        return tf.estimator.EstimatorSpec(mode=mode, train_op=train_op, loss = loss)
    
    elif EVAL:
        loss = tf.losses.sigmoid_cross_entropy(labels, logits)
        pred = tf.nn.sigmoid(logits)
        accuracy = tf.metrics.accuracy(labels, tf.round(pred))
        return tf.estimator.EstimatorSpec(mode=mode, loss=loss, eval_metric_ops={'acc': accuracy})
        
    elif PREDICT:
        return tf.estimator.EstimatorSpec(
            mode=mode,
            predictions={
                'prob': tf.nn.sigmoid(logits),
            }
        )


# In[52]:



def mapping_fn(X, Y):
    input, label = {'x': X}, Y
    return input, label

def test_input_fn():
    dataset = tf.data.Dataset.from_tensor_slices((train_inputs,train_labels))
    dataset = dataset.batch(16)
    dataset = dataset.map(mapping_fn)
    iterator = dataset.make_one_shot_iterator()
    
    return iterator.get_next()


# In[53]:


def tagging():
    model_fn2 = tf.estimator.Estimator(model_fn, model_dir="C:/Users/HojinLee/IdeaProjects/Capstone/src/main/java/com/capstone/springboot/python/data_out/checkpoint/cnn_model")
    ### predict 계산
    predict = model_fn2.evaluate(test_input_fn)
    result = predict.get('acc')
    print("label : ",label)
    print("result : ",result)
    if label ==0 :
        print ("1")
    if label ==1:
        if result ==0: print ("1")
        elif result ==1 : print ("0")


# In[54]:


tagging()


# In[55]:


# In[ ]:





# In[ ]:




