# Introduce This Project
## + Why Develop This Project And Others
* This is the homework project of CS3025301 Software Engineering Course  of 2016 Fall Semester in NTUST. 
* This project uses jdk1.7 and the IDE is eclipse-neon-2.(Util Now 2016-12-25-15:57)
* This project uses the MVC architecture pattern.
    
## + Introduction For TetrisGame Rules(俄羅斯方塊遊戲规则介绍)
####Introduction
一塊積木由四個小方塊組成，所有的積木形狀如下，在格子區域中，一個小方塊會占用一個格子，已經被占的格子不能再被占用。  
玩家利用數種積木在一個長方形的格子區域中進行排列，積木會出現在格子區域的上方，隨著時間積木會往下掉。
####Game Rules And Scoring Rules 
1. 玩家可以將積木向左移、向右移、旋轉、或直接向下丟到底層。  
2. 積木掉下過程中，如過積木下緣遇到已被占用的格子，則積木無法再往下掉，遊戲會再給一塊新的積木在格子區域上，進行下一塊積木的擺放。回到步骤2。  
3. 當格子區域中的任何一列因積木的擺放而被填滿時，則整列會被消除，所有該列上方的小方塊都會往下移，直到下緣碰到其他方塊或格子區域的邊緣。  
4. 每消除一列，用户得到一定积分。  

####When Game Over
遊戲進行直到積木堆積到格子區域頂端，無法再擺放新的積木為止。用户也可以自己选择退出游戏。 
