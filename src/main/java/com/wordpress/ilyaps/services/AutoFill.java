package com.wordpress.ilyaps.services;

import com.wordpress.ilyaps.Logic.Compliance;
import com.wordpress.ilyaps.dao.MemberDAO;
import com.wordpress.ilyaps.dao.TaskDAO;
import com.wordpress.ilyaps.dao.WantToHelpDAO;
import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.Task;
import com.wordpress.ilyaps.models.University;
import com.wordpress.ilyaps.models.WantToHelp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by ilyap on 22.01.2016.
 */
public class AutoFill {

    public static void main(String[] args) {
        List<String> firstnames = new ArrayList<>(Arrays.asList("Ilya", "Inna", "Andrey", "Timur", "Jenya", "Dima", "Pavel"));
        List<String> surnames = new ArrayList<>(Arrays.asList("Petukhov", "Provorova", "Savchenko", "Arshavin", "Pavelko", "Tsiganov", "Ivanov"));
        List<String> emaildomens = new ArrayList<>(Arrays.asList("@mail.ru", "@yandex.ru", "@gmail.com"));
        List<Integer> passwords = new ArrayList<>(Arrays.asList("12345".hashCode(), "1234".hashCode()));

        List<String> title1 = new ArrayList<>(Arrays.asList("Помогите решить задачу на ", "В универе задали задачу по "));
        List<String> title2 = new ArrayList<>(Arrays.asList("Pascal", "C", "C++", "Python", "Python + django", "Java"));

        List<String> text1 = new ArrayList<>(Arrays.asList("В задаче нужно вывести на экран ", "В консольной программе посчитать "));
        List<String> text21 = new ArrayList<>(Arrays.asList(" одномерный массив отсортированный по возрастанию ", " одномерный массив отсортированный по убыванию ", " массив без отрицательных элементов ", " двумерный массив без нулевых элементов "));
        List<String> text22 = new ArrayList<>(Arrays.asList(" дисперсию двух величин ", " сумму чисел ", " интеграл функции y = x ^ 2 на отрезке ", " произведение чисел ", " среднее геометрическое для  ", " среднее арифметическое "));

        int maxMembers = 10;
        int maxTasks = 100;
        Random random = new Random();
        List<String> listUniversity = new ArrayList<String>(University.getSet());

        List<Member> members = new ArrayList<>();
        for (int i = 0; i < maxMembers; ++i) {
            Member member = new Member();
            String firstname = firstnames.get(random.nextInt(firstnames.size()));

            member.setEmail(firstname.toLowerCase() + i + emaildomens.get(random.nextInt(emaildomens.size())));
            member.setSurname( surnames.get(random.nextInt(surnames.size())));
            member.setFirstname(firstname);
            member.setUniversityShortName(listUniversity.get(random.nextInt(listUniversity.size())));
            member.setHashPassword(passwords.get(random.nextInt(passwords.size())));

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
                text =  text22.get(random.nextInt(text22.size())) + random.nextInt() + " " + random.nextInt();
            }

            task.setText(text1.get(random.nextInt(text1.size())) + text);

            TaskDAO.insert(task);
            tasks.add(task);
        }

        List<String> notes = new ArrayList<>(Arrays.asList("еду", "деньги", " только за доллары", "евро", "поцелуй", "бесплтно"));
        int maxWantToHelp = 300;
        for (int i = 0; i < maxWantToHelp; ++i) {
            WantToHelp help = new WantToHelp();

            Member helper = members.get(random.nextInt(members.size()));
            Task task = tasks.get(random.nextInt(tasks.size()));
            Member need = task.getMemberNeed();

            help.setMemberHelper(helper);
            help.setNote("Это легко, помогу за  " + notes.get(random.nextInt(notes.size())));
            help.setLevelOfCompliance(Compliance.getComplianceMembers(helper, need));

            task.addWantToHelp(help);
        }


    }
}
