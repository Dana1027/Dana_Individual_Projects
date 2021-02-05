#!/usr/bin/env python
# coding: utf-8

# # Import libraries

# In[131]:


import matplotlib.pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
import numpy as np
import pandas as pd
from sklearn import datasets, linear_model
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error
import math
import os


# ## Check the directory

# In[18]:


path = os.getcwd()
print(path)


# In[19]:


#change the directoy
os.chdir('c:/Users/hyda1/OneDrive/Winter2021/COURSERA ml')


# In[20]:


os.getcwd()


# # Data Analysis

# In[72]:


#import home_data.csv
sales = pd.read_csv('home_data.csv')
sales


# In[22]:


#check the head of the data -> automatically shows 5 rows of the data set
sales.head()


# In[23]:


# taking 'squft_living' only from the entire data set
sales_squ = sales['sqft_living']


# In[24]:


print(sales_squ)


# In[73]:


#summary of statistics
sales.describe()


# In[67]:


# group by zipcode and then calculate the mean of the price
avg_zip_price = sales.groupby(['zipcode'])['price'].mean() 
avg_zip_price


# In[68]:


#Find the max value among the mean
avg_zip_price.max()


# In[70]:


#find the row where it has the max values
avg_zip_price[avg_zip_price == 2160606.6]


# In[91]:


#Filtering data: What fraction of the houses have living space between 2000 sq.ft. and 4000 sq.ft.?
fraction = sales[(sales['sqft_living'] > 2000) & (sales['sqft_living'] < 4000)]
fraction


# In[92]:


print(len(fraction))


# In[93]:


#The rounded up value of the fraction between 2000 and 4000 
print('The fraction of the houses have living space between 2000 sq.ft. and 4000 sq.ft is approximately', 
      round(len(fraction)/len(sales), 2))


# In[ ]:


#3. Building a regression model with several more features:  
#In the sample notebook, we built two regression models to predict house prices, 
#one using just ‘sqft_living’ and the other one using a few more features, we called this set


# In[94]:


my_features = ['bedrooms', 'bathrooms', 'sqft_living', 'sqft_lot', 'floors', 'zipcode']
advanced_features = [
'bedrooms', 'bathrooms', 'sqft_living', 'sqft_lot', 'floors', 'zipcode',
'condition', # condition of house				
'grade', # measure of quality of construction				
'waterfront', # waterfront property				
'view', # type of view				
'sqft_above', # square feet above ground				
'sqft_basement', # square feet in basement				
'yr_built', # the year built				
'yr_renovated', # the year renovated				
'lat', 'long', # the lat-long of the parcel				
'sqft_living15', # average sq.ft. of 15 nearest neighbors 				
'sqft_lot15', # average lot size of 15 nearest neighbors 
]


# In[97]:


#slicing the data as traing and test data
train_data, test_data = train_test_split(sales, test_size=0.2)


# In[103]:


#check whether both data have right amount of the data inside
print(round(len(train_data)/len(sales), 2))
print(round(len(test_data)/len(sales), 2))


# In[113]:


sqft = train_data['sqft_living']
sqft


# In[120]:


x_train = train_data['sqft_living'].values.reshape((-1,1))
x_train


# In[115]:


x_test = test_data['sqft_living'].values.reshape((-1,1))
x_test


# In[121]:


y_train = train_data['price'].values.reshape((-1,1))
y_train


# In[117]:


y_test = test_data['price'].values.reshape((-1,1))
y_test


# # Regression

# In[123]:


regression = linear_model.LinearRegression()


# In[125]:


train_model = regression.fit(x_train, y_train)
train_model


# In[126]:


y_pred = train_model.predict(x_test)


# In[129]:


print("Mean squared error: %.2f"
      % mean_squared_error(y_test, y_pred))


# In[130]:


# Calculate Residual sum of squares on test data
print("RMSE: %.2f"
      % math.sqrt(mean_squared_error(y_test, y_pred)))


# In[132]:


plt.plot(test_data['sqft_living'], test_data['price'], '.',
        test_data['sqft_living'], train_model.predict(x_test), '-')

