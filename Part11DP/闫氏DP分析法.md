# 闫氏DP分析法
> 核心：从集合角度来分析DP问题

![闫氏DP分析法图示](04_AcWing动态规划基础/闫氏DP分析法图示.jpg)

## DP的本质
> 有限集合中的最优化问题

## DP的两个阶段
### 1.化零为整
> 即**状态表示**，不是单独对每一个具体的点求最优解，而是把问题划分成多个成递推关系的**子阶段**，各个子阶段之间满足`最优化`原理和`无后效性`原则，一般用$f[i]$或$f[i][j]$表示不同的子阶段

+ 集合：当前的子阶段内问题的所有可能解
  > 叫`阶段`感觉更为合适
+ 属性：上面的集合中最终得到的最优解，即f[i]或f[i][j]的最终存储的值，如`最大值max`、`最小值min`、`计数cnt`、`是否存在bool`等
  > 叫`目标`感觉更为合适
### 2.化整为零
> 即**状态计算**，求当前子阶段集合内**各种情况(`也称问题子集，是对1中划分的子阶段集合的分情况讨论`)的最优解**，取这些情况下的`最大/最小/计数`值(`目标`)，即为f[i]最终的值

+ 数据段内各种情况的划分原则：**不重复**、**不遗漏**
+ 数据段内各种情况的划分方法：**寻找最后一个不同点**

## DP问题优化的本质
> 一般是对代码或者计算方程进行等价变形(`数据结构、计算方式等`)。我们要先掌握好基本的状态表达式，只有基本的状态表达式才能理解通DP问题