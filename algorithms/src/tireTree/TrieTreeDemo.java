package tireTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class TrieTreeDemo {
    static class Node {
        //记录当前节点的字
        char c;
        //判断该字是否词语的末尾，如果是则为false
        boolean isEnd;
        //子节点
        List<Node> childList;

        public Node(char c) {
            super();
            this.c = c;
            isEnd = false;
            childList = new LinkedList<Node>();
        }

        //查找当前子节点中是否保护c的节点
        public Node findNode(char c){
            for(Node node : childList){
                if(node.c == c){
                    return node;
                }
            }

            return null;
        }
    }

    static class TrieTree{
        Node root = new Node(' ');

        //构建Trie Tree
        public void insert(String words){
            char[] arr = words.toCharArray();
            Node currentNode = root;
            for (char c : arr) {
                Node node = currentNode.findNode(c);
                //如果不存在该节点则添加
                if(node == null){
                    Node n = new Node(c);
                    currentNode.childList.add(n);
                    currentNode = n;
                }else{
                    currentNode = node;
                }
            }
            //在词的最后一个字节点标记为true
            currentNode.isEnd = true;
        }

        //判断Trie Tree中是否包含该词
        public boolean search(String word){
            char[] arr = word.toCharArray();
            Node currentNode = root;
            for (int i=0; i<arr.length; i++) {
                Node n = currentNode.findNode(arr[i]);
                if(n != null){
                    currentNode = n;
                    //判断是否为词的尾节点节点
                    if(n.isEnd){
                        if(n.c == arr[arr.length-1]){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        //最大匹配优先原则
        public Map<String, Integer> tokenizer(String words){
            char[] arr = words.toCharArray();
            Node currentNode = root;
            Map<String, Integer> map = new HashMap<String, Integer>();
            //记录Trie Tree 从root开始匹配的所有字
            StringBuilder sb = new StringBuilder();;
            //最后一次匹配到的词，最大匹配原则，可能会匹配到多个字，以最长的那个为准
            String word="";
            //记录记录最后一次匹配坐标
            int idx = 0;
            for (int i=0; i<arr.length; i++) {
                Node n = currentNode.findNode(arr[i]);
                if(n != null){
                    sb.append(n.c);
                    currentNode = n;
                    //匹配到词
                    if(n.isEnd){
                        //记录最后一次匹配的词
                        word = sb.toString();
                        //记录最后一次匹配坐标
                        idx = i;
                    }
                }else{
                    //判断word是否有值
                    if(word!=null && word.length()>0){
                        Integer num = map.get(word);
                        if(num==null){
                            map.put(word, 1);
                        }else{
                            map.put(word, num+1);
                        }
                        //i回退到最后匹配的坐标
                        i=idx;
                        //从root的开始匹配
                        currentNode = root;
                        //清空匹配到的词
                        word = null;
                        //清空当前路径匹配到的所有字
                        sb = new StringBuilder();
                    }
                }
                if(i==arr.length-2){
                    if(word!=null && word.length()>0){
                        Integer num = map.get(word);
                        if(num==null){
                            map.put(word, 1);
                        }else{
                            map.put(word, num+1);
                        }
                    }
                }
            }

            return map;
        }
    }

}
