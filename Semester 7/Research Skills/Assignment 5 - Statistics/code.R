iris

my_mode <- table(iris$Sepal.Length) 
my_mode 
my_mode[which(my_mode ==max(my_mode))] 
my_mode <- table(iris$Sepal.Width) 
my_mode 
my_mode[which(my_mode ==max(my_mode))] 
my_mode <- table(iris$Petal.Length) 
my_mode 
my_mode[which(my_mode ==max(my_mode))]
my_mode <- table(iris$Petal.Width) 
my_mode 
my_mode[which(my_mode ==max(my_mode))]

median(iris$Sepal.Length)
median(iris$Sepal.Width)
median(iris$Petal.Length)
median(iris$Petal.Width)

mean(iris$Sepal.Length)
mean(iris$Sepal.Width)
mean(iris$Petal.Length)
mean(iris$Petal.Width)

min(iris$Sepal.Length)
max(iris$Sepal.Length)
range(iris$Sepal.Length)
min(iris$Sepal.Width)
max(iris$Sepal.Width)
range(iris$Sepal.Width)
min(iris$Petal.Length)
max(iris$Petal.Length)
range(iris$Petal.Length)
min(iris$Petal.Width)
max(iris$Petal.Width)
range(iris$Petal.Width)

quantile(iris$Sepal.Length)
quantile(iris$Sepal.Width)
quantile(iris$Petal.Length)
quantile(iris$Petal.Width)

var(iris$Sepal.Length) 
var(iris$Sepal.Width) 
var(iris$Petal.Length) 
var(iris$Petal.Width)

sd(iris$Sepal.Length) 
sd(iris$Sepal.Width) 
sd(iris$Petal.Length) 
sd(iris$Petal.Width)  

x<-((iris$Sepal.Length)-mean(iris$Sepal.Length))/sd(iris$Sepal.Length)
x 
x<-((iris$Sepal.Width)-mean(iris$Sepal.Width))/sd(iris$Sepal.Width)
x 
x<-((iris$Petal.Length)-mean(iris$Petal.Length))/sd(iris$Petal.Length)
x 
x<-((iris$Petal.Width)-mean(iris$Petal.Width))/sd(iris$Petal.Width)
x 

skewness(iris$Sepal.Length)
skewness(iris$Sepal.Width)
skewness(iris$Petal.Length)
skewness(iris$Petal.Width)

kurtosis(iris$Sepal.Length)
kurtosis(iris$Sepal.Width)
kurtosis(iris$Petal.Length)
kurtosis(iris$Petal.Width)

hist(iris$Sepal.Length, main="Histogram of Sepal.Length", xlab="Sepal.Length")
hist(iris$Sepal.Width, main="Histogram of Sepal.Width", xlab="Sepal.Width")
hist(iris$Petal.Length, main="Histogram of Petal.Length", xlab="Petal.Length")
hist(iris$Petal.Width, main="Histogram of Petal.Width", xlab="Petal.Width")

boxplot(iris$Sepal.Length, main="Boxplot of Sepal.Length")
boxplot(iris$Sepal.Width, main="Boxplot of Sepal.Width")
boxplot(iris$Petal.Length, main="Boxplot of Petal.Length")
boxplot(iris$Petal.Width, main="Boxplot of Petal.Width")

plot(iris$Sepal.Length ~ iris$Sepal.Width, main="Sepal.Length vs Sepal.Width", xlab="Sepal.Width", ylab="Sepal.Length")
plot(iris$Sepal.Length ~ iris$Petal.Length, main="Sepal.Length vs Petal.Length", xlab="Petal.Length", ylab="Sepal.Length")
plot(iris$Sepal.Length ~ iris$Petal.Width, main="Sepal.Length vs Petal.Width", xlab="Petal.Width", ylab="Sepal.Length")
plot(iris$Sepal.Width ~ iris$Petal.Length, main="Sepal.Width vs Petal.Length", xlab="Petal.Length", ylab="Sepal.Width")
plot(iris$Sepal.Width ~ iris$Petal.Width, main="Sepal.Width vs Petal.Width", xlab="Petal.Width", ylab="Sepal.Width")
plot(iris$Petal.Length ~ iris$Petal.Width, main="Petal.Length vs Petal.Width", xlab="Petal.Width", ylab="Petal.Length")