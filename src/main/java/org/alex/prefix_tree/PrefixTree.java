package org.alex.prefix_tree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PrefixTree {
    public static void main(String[] args) throws IOException{
        List<String> lines =  Files.readAllLines(Paths.get(".\\src\\main\\java\\org\\alex\\prefix_tree\\numbers.txt"));

        TreeNode root = new TreeNode(' ');
        for (String line : lines) {
            root.insert(line);
        }

        TreeNode.writeTreeToFile(".\\src\\main\\java\\org\\alex\\prefix_tree\\tree.dat", root);

        TreeNode fromFile = TreeNode.readTreeFromFile(".\\src\\main\\java\\org\\alex\\prefix_tree\\tree.dat");

        List<String> numbersList = new ArrayList<>();

        fromFile.getAllNumbers("", numbersList);
        for (String number: numbersList) {
            System.out.println(number);
        }
        System.out.println(fromFile.containString("18АТ0471"));
    }

    public static class TreeNode{
        char value;
        List<TreeNode> childrens;

        public TreeNode(char value) {
            this.value = value;
        }

        public void insert(String data){
            if (data.length() == 0)
                return;

            if (childrens == null)
                childrens = new ArrayList<>();

            char ch = data.charAt(0);
            TreeNode child = findNodeByChar(ch);
            if (child == null){
                child = new TreeNode(ch);
                childrens.add(child);
            }
            child.insert(data.substring(1));
        }

        private TreeNode findNodeByChar(char ch){
            if (childrens != null){
                for (TreeNode node: childrens) {
                    if (node.value == ch)
                        return node;
                }
            }
            return null;
        }

        private boolean containString(String data){
            TreeNode current = this;
            for (int i = 0; i < data.length(); i++) {
                current = current.findNodeByChar(data.charAt(i));
                if (current == null)
                    return false;
            }
            return true;
        }

        public void getAllNumbers(String path, List<String> result){
            if (value != ' ')
                path = path + value;

            if (childrens != null){
                for (TreeNode child : childrens) {
                    child.getAllNumbers(path, result);
                }
            } else{
                result.add(path);
            }
        }

        public void writeToFile(PrintWriter writer){
            writer.write(value);
            if (childrens !=null){
                for (TreeNode child : childrens)
                    child.writeToFile(writer);
            }
            writer.write(']');
        }

        public void readFromFile(FileReader reader) throws IOException {
            char ch;
            while((ch = (char)reader.read()) != ']'){
                TreeNode node = new TreeNode(ch);
                node.readFromFile(reader);
                if (childrens == null)
                    childrens = new ArrayList<>();
                childrens.add(node);
            }
        }

        public static boolean writeTreeToFile(String path, TreeNode root){
            try{
                PrintWriter out = new PrintWriter(path);
                root.writeToFile(out);
                out.flush();
                out.close();
                return true;
            }catch (FileNotFoundException e){
                e.printStackTrace();
                return false;
            }
        }

        public static TreeNode readTreeFromFile(String path){
            TreeNode root = new TreeNode(' ');
            try{
                FileReader reader = new FileReader(path);
                reader.read();
                root.readFromFile(reader);
                reader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            return root;
        }

    }
}
