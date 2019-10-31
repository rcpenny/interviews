整理so good

https://paper.dropbox.com/doc/Dropbox-NM0Ap7llxjNJW3G7oQAV8

file access. 地理很多 簡單來說就是給你一個你能access的目錄 (Data type: Set<String>) 和這些目錄的繼承關係(Data type: List<String[]>). 有母目錄的權限就能去子目錄. function input 目錄名稱， 回傳true or false.
要注意（我沒注意到...） 目錄的繼承關係不要預設是有順序的，雖然看起來有順序

Follow up:
redundant access. 假設目錄長這樣：
A
|--------B
           |--------D
|--------C
           |--------E

然後 access = [B, C], 修改Access成 access = [A] 因為有Ａ就可以有B & C
假如 access = [D, E], 則回傳 access = [D, E]


https://aonecode.com/search-byte-file
