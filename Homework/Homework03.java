// public class Homework03 {
//     public class HashMap {
//         private class Entity {
//             int Key;
//             int Value;
//         }

//         private class Basket {
//             Node Head;

//             private class Node {
//                 Entity entity;
//                 Node next;
//             }

//             Entity find(int Key) {
//                 Node current = Head;
//                 if (Head != null) {
//                     while (current != null) {
//                         if (current.entity.Key == Key) {
//                             return current.entity;
//                         }
//                         current = current.next;
//                     }
//                 }
//                 return null;
//             }

//             private boolean push(Entity entity) {
//                 Node node = new Node();
//                 node.entity = entity;

//                 Node current = Head;
//                 while (current != null) {
//                     if (current.entity == entity) {
//                         return false;
//                     }
//                     if (current.next == null) {
//                         current.next = node;
//                         return true;
//                     }
//                     current = current.next;
//                 }
//                 Head = node;
//                 return true;
//             }

//             private boolean del(int Key) {
//                 Node current = Head;
//                 while (current != null) {
//                     if (current.next.entity.Key == Key) {
//                         if (current.next != null) {
//                             current.next = current.next.next;
//                         } else {
//                             current.next = null;
//                         }
//                         return true;
//                     }
//                     current = current.next;
//                 }
//                 return false;
//             }
//         }

//         private static final int INIT_SIZE = 16;
//         private static final int Size = 0;
//         private static final double LOAD_FACTOR = 0.75;

//         private Basket baskets[];

//         public HashMap() {
//             this(INIT_SIZE);
//         }

//         public HashMap(int size) {
//             baskets = new Basket[size];
//         }

//         int calcIndex(int Key) {
//             return Key % baskets.length;
//         }

//         public Entity find(int Key) {
//             int index = calcIndex(Key);
//             Basket basket = baskets[index];

//             if (basket != null) {
//                 return basket.find(Key);
//             }
//             return null;
//         }

//         public void push(int Key, int Value) {
//             int index = calcIndex(Key);
//             Basket basket = baskets[index];

//             Entity entity = new Entity();
//             entity.Value = Value;
//             entity.Key = Key;

//             if (basket == null) {
//                 basket = new Basket();
//                 baskets[index] = basket;
//             }

//             basket.push(entity);

//         }

//         public void del(int Key) {
//             int index = calcIndex(Key);
//             Basket basket = baskets[index];

//             if (basket != null) {
//                 basket.del(Key);
//             }
//         }
//     }

//     public class BinaryTree {
//         Node Root;

//         private class Node {
//             int Value;
//             Node Left;
//             Node Right;
//         }

//         public boolean find(int Value) {
//             Node current = Root;
//             while (current != null) {
//                 if (current.Value == Value)
//                     return true;

//                 if (Value < current.Value) {
//                     current = current.Left;
//                 } else {
//                     current = current.Right;
//                 }
//             }
//             return false;
//         }

//         public void push(int Value) {
//             Node node = new Node();
//             node.Value = Value;
//             if (Root == null) {
//                 Root = node;
//             } else {
//                 Node current = Root;

//                 while (current != null) {
//                     if (Value < current.Value) {
//                         if (current.Left == null) {
//                             current.Left = node;
//                             return;
//                         }
//                         current = current.Left;
//                     } else {
//                         if (current.Right == null) {
//                             current.Right = node;
//                             return;
//                         }
//                         current = current.Right;
//                     }
//                 }

//             }
//         }
//     }

//     public static void main(String[] args) {

//     }
// }

// Необходимо превратить собранное на семинаре дерево поиска в полноценное левостороннее красно-черное дерево. 
// И реализовать в нем метод добавления новых элементов с балансировкой.

import java.util.Scanner;

class node {

  node left, right;
  int data;

  // красный ==> true, черный ==> false
  boolean color;

  node(int data) {
    this.data = data;
    left = null;
    right = null;

// Новый узел, который создается, является всегда красного цвета.
    color = true;
  }
}

public class Homework03 {

  private static node root = null;

  // Функция для поворота узла против часовой стрелки.
  node rotateLeft(node myNode) {
    System.out.printf("поворот влево!!\n");
    node child = myNode.right;
    node childLeft = child.left;

    child.left = myNode;
    myNode.right = childLeft;

    return child;
  }

  // Функция для поворота узла по часовой стрелке.
  node rotateRight(node myNode) {
    System.out.printf("вращение вправо\n");
    node child = myNode.left;
    node childRight = child.right;

    child.right = myNode;
    myNode.left = childRight;

    return child;
  }

  // Функция для проверки того, является ли узел красного цвета или нет.
  boolean isRed(node myNode) {
    if (myNode == null) {
      return false;
    }
    return (myNode.color == true);
  }

  // Функция для изменения цвета двух узлы.
  void swapColors(node node1, node node2) {
    boolean temp = node1.color;
    node1.color = node2.color;
    node2.color = temp;
  }

  // вставка в левостороннее Красно-черное дерево.
  node insert(node myNode, int data) {
// Обычный код вставки для любого двоичного файла
    if (myNode == null) {
      return new node(data);
    }

    if (data < myNode.data) {
      myNode.left = insert(myNode.left, data);
    } else if (data > myNode.data) {
      myNode.right = insert(myNode.right, data);
    } else {
      return myNode;
    }

// случай 1.
    // когда правый дочерний элемент красный, а левый дочерний элемент черный или не существует.
    if (isRed(myNode.right) && !isRed(myNode.left)) {
// Повернуть узел  влево
      myNode = rotateLeft(myNode);

// Поменять местами цвета дочернего узла всегда должен быть красным
      swapColors(myNode, myNode.left);
    }

// случай 2
    // когда левый ребенок, а также левый внук выделены красным цветом
    if (isRed(myNode.left) && isRed(myNode.left.left)) {
// Повернуть узел в право
      myNode = rotateRight(myNode);
      swapColors(myNode, myNode.right);
    }

// случай 3
    // когда и левый, и правый дочерние элементы окрашены в красный цвет.
    if (isRed(myNode.left) && isRed(myNode.right)) {
// Инвертировать цвет узла это левый и правый дети.
      myNode.color = !myNode.color;

// Изменить цвет на черный.
      myNode.left.color = false;
      myNode.right.color = false;
    }

    return myNode;
  }

  // Обход по порядку
  void inorder(node node) {
    if (node != null)
    {
      inorder(node.left);
      char c = '●';
      if (node.color == false)
        c = '◯';
      System.out.print(node.data + ""+c+" ");
      inorder(node.right);
    }
  }

  public static void main(String[] args) {

    Homework03 node = new Homework03();
    Scanner scan = new Scanner(System.in);

    char ch;
    do {
      System.out.println("Введите целое число");

      int num = scan.nextInt();
      root = node.insert(root, num);

      node.inorder(root);
      System.out.println("\nВы хотите продолжить? (введите y или n)");
      ch = scan.next().charAt(0);
    } while (ch == 'Y' || ch == 'y');
  }
}