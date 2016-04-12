package com.wordpress.ilyaps;


import com.wordpress.ilyaps.logic.Compliance;
import com.wordpress.ilyaps.dao.*;
import com.wordpress.ilyaps.models.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AutoFill {
    static long offset = Timestamp.valueOf("2016-01-01 00:00:00").getTime();
    static long end = Timestamp.valueOf("2016-04-10 00:00:00").getTime();
    static long diff = end - offset + 1;

    public static void main(String[] args) {

        BaseDAO.drop(Book.class);
        BaseDAO.drop(WantToHelp.class);
        BaseDAO.drop(Task.class);
        BaseDAO.drop(Member.class);
        BaseDAO.drop(University.class);
        BaseDAO.drop(Category.class);
        BaseDAO.drop(LikeBook.class);
        BaseDAO.drop(LikeWantToHelp.class);

        List<String> firstnames = new ArrayList<>(Arrays.asList("Ilya", "Inna", "Andrey", "Timur", "Jenya", "Dima", "Pavel"));
        List<String> surnames = new ArrayList<>(Arrays.asList("Petukhov", "Provorova", "Savchenko", "Arshavin", "Putin", "Tsiganov", "Ivanov"));
        List<String> emaildomens = new ArrayList<>(Arrays.asList("@mail.ru", "@yandex.ru", "@gmail.com"));
        List<Integer> passwords = new ArrayList<>(Arrays.asList("12345".hashCode(), "1234".hashCode()));

        List<String> title1 = new ArrayList<>(Arrays.asList("Помогите решить задачу на ", "В универе задали задачу по "));
        List<String> title2 = new ArrayList<>(Arrays.asList("Pascal", "C", "C++", "Python", "Python + django", "Java"));

        List<String> text1 = new ArrayList<>(Arrays.asList("В задаче нужно вывести на экран ", "В консольной программе посчитать "));
        List<String> text21 = new ArrayList<>(Arrays.asList(" одномерный массив отсортированный по возрастанию ", " одномерный массив отсортированный по убыванию ", " массив без отрицательных элементов ", " двумерный массив без нулевых элементов "));
        List<String> text22 = new ArrayList<>(Arrays.asList(" дисперсию двух величин ", " сумму чисел ", " интеграл функции y = x ^ 2 на отрезке ", " произведение чисел ", " среднее геометрическое для  ", " среднее арифметическое "));

        Random random = new Random();
        int maxMembers = 10 + random.nextInt(20);
        int maxTasks = 100 + random.nextInt(200);

        BaseDAO.insert(new University("bmstu", "МГТУ"));
        BaseDAO.insert(new University("msu", "МГУ"));
        BaseDAO.insert(new University("spbu", "СПБГУ"));
        BaseDAO.insert(new University("rsuh", "РГГУ"));

        List<String> listUniversity = new ArrayList<>(UniversityDAO.getSet());

        BaseDAO.insert(new Category("empty"));
        BaseDAO.insert(new Category("Algorithms"));
        BaseDAO.insert(new Category("Structured"));
        BaseDAO.insert(new Category("OOP"));
        BaseDAO.insert(new Category("Front-end"));
        BaseDAO.insert(new Category("Back-end"));
        BaseDAO.insert(new Category("Database"));

        List<Category> listCategory = new ArrayList<>(CategoryDAO.findAll());

        BaseDAO.insert(new Book("ООАиП", "Гради Буч", listCategory.get(random.nextInt(listCategory.size()))));
        BaseDAO.insert(new Book("Современные операционные системы", "Таненбаум", listCategory.get(random.nextInt(listCategory.size()))));
        BaseDAO.insert(new Book("Архитектура ЭВМ", "Таненбаум", listCategory.get(random.nextInt(listCategory.size()))));
        BaseDAO.insert(new Book("Паттерны проектирования", "Банда 4-х", listCategory.get(random.nextInt(listCategory.size()))));
        BaseDAO.insert(new Book("Unix проф программирование", "Стивен Раго", listCategory.get(random.nextInt(listCategory.size()))));
        BaseDAO.insert(new Book("Скала для нетерпеливых", "Хорстман", listCategory.get(random.nextInt(listCategory.size()))));
        BaseDAO.insert(new Book("Язык программирования go", "Керниган", listCategory.get(random.nextInt(listCategory.size()))));

        List<Member> members = new ArrayList<>();
        for (int i = 0; i < maxMembers; ++i) {
            Member member = new Member();
            String firstname = firstnames.get(random.nextInt(firstnames.size()));

            member.setEmail(firstname.toLowerCase() + i + emaildomens.get(random.nextInt(emaildomens.size())));
            member.setSurname( surnames.get(random.nextInt(surnames.size())));
            member.setFirstname(firstname);
            member.setUniversityShortName(listUniversity.get(random.nextInt(listUniversity.size())));
            member.setHashPassword(passwords.get(random.nextInt(passwords.size())));
            member.setLikes(random.nextInt(10));

            members.add(member);
            MemberDAO.insert(member);
        }

        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < maxTasks; ++i) {
            Task task = new Task();
            task.setMemberNeed(members.get(random.nextInt(members.size())));
            task.setTitle(title1.get(random.nextInt(title1.size())) + title2.get(random.nextInt(title2.size())));

            String text;
            if (random.nextBoolean()) {
                text = text21.get(random.nextInt(text21.size()));
            } else {
                text =  text22.get(random.nextInt(text22.size())) + (random.nextInt(1000) - 100) + " " + (random.nextInt(1000) + 100);
            }

            task.setCategory(listCategory.get(random.nextInt(listCategory.size())));
            task.setText(text1.get(random.nextInt(text1.size())) + text);
            task.setDateTimeField(new Timestamp(offset + (long)(Math.random() * diff)));
            TaskDAO.insert(task);
            tasks.add(task);
        }

        List<String> notes = new ArrayList<>(Arrays.asList("еду", "деньги", " только доллары", "евро", "поцелуй", "бесплтно"));
        int maxWantToHelp = 300 + random.nextInt(300);
        for (int i = 0; i < maxWantToHelp; ++i) {
            WantToHelp help = new WantToHelp();

            Member helper = members.get(random.nextInt(members.size()));
            Task task = tasks.get(random.nextInt(tasks.size()));
            Member need = task.getMemberNeed();

            help.setMemberHelper(helper);
            help.setNote("Это легко, помогу за  " + notes.get(random.nextInt(notes.size())));
            help.setLevelOfCompliance(Compliance.getComplianceMembers(helper, need));

            WantToHelpDAO.addWantToHelp(task, help);
        }


    }
}
