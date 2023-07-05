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