// find 1st position of target...

public class FindBadVersion {
  
  public int findFirstBadVersion(int n) {
    int start = 1, end = n;
    
    while (start + 1 < end) {
      int middle = start + (end - start) / 2;
      if (SVNRepo.isBadVersion(middle)) 
        end = middle;
      else 
        start =middle;
    }
    
    if (SVNRepo.isBadVersion(start)) return start;
    return end;
  }
}
