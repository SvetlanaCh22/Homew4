/*
Необходимо превратить собранное на семинаре дерево поиска в полноценное левостороннее
(по желанию можно обычное) красно-черное дерево. И реализовать в нем метод добавления новых
элементов с балансировкой.

Красно-черное дерево имеет следующие критерии:
• Каждая нода имеет цвет (красный или черный)
• Корень дерева всегда черный
• Новая нода всегда красная
• Красные ноды могут быть только левым ребенком
• У красной ноды все дети черного цвета

Соответственно, чтобы данные условия выполнялись, после добавления элемента
в дерево необходимо произвести балансировку, благодаря которой все критерии
выше станут валидными. Для балансировки существует 3 операции – левый малый
поворот, правый малый поворот и смена цвета.

Автор: Чубченко Светлана
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        // создаем красно-черное дерево
        final RedBlackTree tree = new RedBlackTree();
        // читаем значения из консоли для заполнения дерева
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // бесконечный цикл вводимых значений пользователем
            while (true) {
                // игнорируем ошибки ввода пользователя
                try {
                    // считываем число из консоли
                    int value = Integer.parseInt(reader.readLine());
                    // добавляем в красно-черное дерево балансируя
                    tree.add(value);
                    // здесь можем установить почку останова, чтобы в отладке смотреть как
                    // меняется дерево
                    System.out.println("added");
                } catch (Exception ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

