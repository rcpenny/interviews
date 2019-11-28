import java.util.ArrayList;

// 输入：
// ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
// 输出：  
// [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

// 最终输出不需要顺序
// 您可以假设目录名、文件名和文件内容只有字母和数字，并且文件内容的长度在 [1，50] 的范围内
// 给定的文件数量在 [1，20000] 个范围内
// 您可以假设在同一目录中没有任何文件或目录共享相同的名称
// 您可以假设每个给定的目录信息代表一个唯一的目录。目录路径和文件信息用一个空格分隔

/*
	1. result order?
	2. file name, file content and dir path contains special chars?
	3. file amount?
	4. same file names in a directory??
	5. is the pattern alway true, separated by one space?
*/

// leet609

class FileInfo {
	String fileName;
	String fileContent;

	FileInfo(String name, String content) {
		this.fileName = name;
		this.fileContent = content;
	}
}

class FindDuplicateFileInSystem {
  public List<List<String>> findDuplicate(String[] paths) {
		List<List<String>> dupeFiles = new ArrayList<>();
		Map<String, List<String>> contentToPaths = new HashMap<>();

		for (String path : paths) {
			String[] strs = path.split(" ");

			String dir = strs[0] + "/";
			FileInfo info;

			for (int i = 1; i < strs.length; i++) {
        info = getFileInfo(strs[i]);
        contentToPaths.putIfAbsent(info.fileContent, new ArrayList<>());
        contentToPaths.get(info.fileContent).add(dir + info.fileName);
      }
		}

    for (String content : contentToPaths.keySet()) {
      if (contentToPaths.get(content).size() > 1)
        dupeFiles.add(new ArrayList<>(contentToPaths.get(content)));
    }

		return dupeFiles;
	}

	private FileInfo getFileInfo(String file) {
		int index = file.indexOf('(');
		return new FileInfo(file.substring(0, index), file.substring(index + 1));
	}
}

/* Follow-up:
	Imagine you are given a real file system, how will you search files? DFS or BFS?
	If the file content is very large (GB level), how will you modify your solution?
	If you can only read the file by 1kb each time, how will you modify your solution?
	What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
	How to make sure the duplicated files you find are not false positive?
*/
