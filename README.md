# 多執行緒

## 停止執行序

* 使用退出標誌
* 使用stop()方法(作廢)
* 使用interrupt()方法
    * 線程Ｂ可以去調用線程Ａ的該方法,將標誌設為true,但如果線程Ａ的狀態為wait、join、sleep阻塞掛起，線程Ａ則會拋出InterruptedException




:::warning
不管調用順序,只要sleep與interrupt碰到就會拋出異常,並且重置標籤
:::

### 判斷當前執行緒是否停止狀態

* boolean interrupt()
    * 當調用interrupt()後**狀態會被清除**
      ex:第一次調用為ture在調用一次為false
    * 測試**當前線程**是否中斷（thread.interrupted()與Thread.interrupted()是一樣的）
    * static方法,可以直接透過Thread類調用
* boolean isInterrupted()
    * 測試==所在線程==是否中斷,不會清除標籤
```
//測試當前線程是否已經中斷
//內部是獲取當前調用線程的中斷標誌,而不是調用interrupted()方法的實例對象的中斷標籤
public static boolean interrupted() {
        return currentThread().isInterrupted(true);
    }
//測試所在線程是否中斷,不會清除標籤
public boolean isInterrupted() {
        return isInterrupted(false);
    }
```

### 暫停線程

- LockSupport.park();
  - 將線程暫停
- LockSupport.uppark();
  - 恢復線程

### 守護線程

當進程中不存在非守護線程時,則守護線程會自動銷毀

例如：ＧＣ回收線成.當沒有非守護線程,也不需要ＧＣ回收了

thread.BsetDaemon(true)//設定守護線程

### 鎖與對象

:::warning
使用synchronized聲明的發法,所在類的對象就是鎖
在JAVA當中沒有鎖方法這種概念,鎖是對象

只有**共享資源**的**寫**訪問才需要同步化
:::

- Ａ線程先持有Object對象的鎖，Ｂ線程可以以異部的方式調用Object對象中非synchronized類型的方法
- Ａ線程先持有Object對象的鎖，Ｂ線程如果在這時間調用object對象中synchronized類型的方法則需等代,也就是同步
- 在方法聲明處添加synchronized並不是鎖方法,是鎖當前類的對象
- 在java只有將對象視為鎖,並沒有鎖方法的說法
- 如果在Ｘ對象使用了synchronized非靜態方法,那Ｘ對象就被當成鎖
- synchronized加到static靜態方法是將class類對象視為鎖,非靜態方法則為所在類的對象視為鎖
- synchronized（class）可以對類的所有對象起作用
- 鎖就是對象,對象可以映射成鎖,哪個線程拿到這把鎖，該線程就可以執行這個對象中的synchronized同步方法


### 重入鎖

- 自己可以再次獲取自己的內部鎖（鎖還沒釋放的情況下可以再次獲得鎖）
- 鎖重入也支持父子類繼承的環境（獲得子類別鎖可以再次獲得父類別鎖）

<aside>
💡 如果子類別Ａ方法上鎖,使用子類未上鎖Ｂ方法調用上鎖父類別方法是不可以的

</aside>

### **釋放鎖**

1.當線程的同步方法、同步代碼塊執行結束

2.當前線程在同步代碼塊、同步方法中遇到<font color="red">break，return</font>

3.當前線程在同步代碼塊、同步方法中出現了<font color="red">未處理的error或exceptio導致異常結束</font>

4.<>當前線程在同步代碼塊、同步方法中<font color="red">執行了線程對象的wait()方法</font>當前線程暫停並釋放鎖

**不會釋放鎖**

1.當前線程在同步代碼塊、同步方法時，程序調用Thread.sleep()、Thread.yield()方法暫停當前線程的執行，不會釋放鎖

2.當前線程在同步代碼時，其他線程調用了該線程的suspend()方法將該線程掛起，該線程不會釋放鎖

### 異動鎖

:::warning
一但持有鎖後就不再對鎖對象進行修改,一但更改就有可能發生錯誤

如果AB線程同時競爭一把鎖,A線程將鎖修改,此時AB線程所競爭的仍是同一把鎖（同一個內存空間）

如果A線程取到鎖後,將鎖做修改,此時B線程再去取A線程使用的鎖,會取到修改後的鎖（不同內存空間）,造成AB線程的異步行為

所以如果是改變屬性值並不會影響鎖
:::