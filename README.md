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