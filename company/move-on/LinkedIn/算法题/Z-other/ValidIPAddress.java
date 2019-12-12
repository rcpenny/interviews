public class ValidIPAddress {
  String IPv6char = "0123456789abcdefABCDEF";
  
  public boolean isVaild_IPv4Number(String str){
      int num = 0;
      if(str.equals("") || str.length() > 3) {    
          return false;
      }
      if(str.length() > 1 && str.charAt(0) == '0' ) {
          return false;
      }
      for (int i = 0; i < str.length(); ++i){    
          if ( !Character.isDigit(str.charAt(i))) {    
              return false;    
          }
          else num = num * 10 + str.charAt(i)-'0';
      }
      if(num >= 0 && num < 256) {
          return true;
      }
      else return false;
  }
  
  public boolean isVaild_IPv6Number(String str) {
    if(str.equals("") || str.length() > 4) return false;
    
    for (int i = 0; i < str.length(); ++i)
      if (IPv6char.indexOf(str.charAt(i)) == -1)  
        return false;    

    return true;
  }

  public int NumberOftoken(String IP,char token){
      int num = 0;
      for (int i = 0; i< IP.length(); ++i){
          if( IP.charAt(i) == token) {
              num ++;
          }
      }
      return num;
  }
  public String validIPAddress(String IP) {
      
      String[] IPv4 = IP.split("\\.");
      boolean is_IPv4 = true;
      if(IPv4.length == 4 && NumberOftoken(IP,'.') == 3) {
          for(int i = 0; i < 4; i++) {
              if(!isVaild_IPv4Number(IPv4[i])){
                  is_IPv4 = false;
                  break;
              }
          }
      }
      else is_IPv4 = false ;
      if(is_IPv4)return "IPv4";
      
      String[] IPv6 = IP.split(":");
      boolean is_IPv6 = true;
      if(IPv6.length == 8 && NumberOftoken(IP,':') == 7) {
          for(int i = 0; i < 8; i++){
              if(!isVaild_IPv6Number(IPv6[i])){
                  is_IPv6 = false;
                  break;
              }
              
          }
      }
      else is_IPv6 = false;
      if(is_IPv6)return "IPv6";
      
      return "Neither";
  }
}