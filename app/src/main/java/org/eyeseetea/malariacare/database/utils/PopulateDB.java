package org.eyeseetea.malariacare.database.utils;

import android.content.res.AssetManager;

import com.opencsv.CSVReader;

import org.eyeseetea.malariacare.database.model.Answer;
import org.eyeseetea.malariacare.database.model.CompositiveScore;
import org.eyeseetea.malariacare.database.model.Header;
import org.eyeseetea.malariacare.database.model.Option;
import org.eyeseetea.malariacare.database.model.OrgUnit;
import org.eyeseetea.malariacare.database.model.Program;
import org.eyeseetea.malariacare.database.model.Question;
import org.eyeseetea.malariacare.database.model.Survey;
import org.eyeseetea.malariacare.database.model.Tab;
import org.eyeseetea.malariacare.database.model.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PopulateDB {

    static Map<Integer, Tab> tabList = new LinkedHashMap<Integer, Tab>();
    static Map<Integer, Header> headerList = new LinkedHashMap<Integer, Header>();
    static Map<Integer, Question> questionList = new LinkedHashMap<Integer, Question>();
    static Map<Integer, Option> optionList = new LinkedHashMap<Integer, Option>();
    static Map<Integer, Answer> answerList = new LinkedHashMap<Integer, Answer>();
    static Map<Integer, CompositiveScore> compositiveScoreList = new LinkedHashMap<Integer, CompositiveScore>();

    //static Map<Integer, Header> headerCustomList = new LinkedHashMap<Integer, Header>();
    //static Map<Integer, Question> questionCustomList = new LinkedHashMap<Integer, Question>();

    public static void populateDB(AssetManager assetManager) throws IOException {


        List<String> tables2populate = Arrays.asList("Tabs.csv", "Headers.csv", "Answers.csv", "Options.csv", "CompositiveScores.csv", "Questions.csv"); //"HeadersCustom.csv", "QuestionsCustom.csv");

        CSVReader reader = null;
        for (String table : tables2populate) {
            reader = new CSVReader(new InputStreamReader(assetManager.open(table)), ';', '\'');

            String[] line;
            while ((line = reader.readNext()) != null) {
                switch (table) {
                    case "Tabs.csv":
                        Tab tab = new Tab();
                        tab.setName(line[1]);
                        tab.setOrder_pos(Integer.valueOf(line[2]));
                        tabList.put(Integer.valueOf(line[0]), tab);
                        break;
                    case "Headers.csv":
                        Header header = new Header();
                        header.setShort_name(line[1]);
                        header.setName(line[2]);
                        header.setOrder_pos(Integer.valueOf(line[3]));
                        header.setTab(tabList.get(Integer.valueOf(line[4])));
                        headerList.put(Integer.valueOf(line[0]), header);
                        break;
                    case "Answers.csv":
                        Answer answer = new Answer();
                        answer.setName(line[1]);
                        answer.setOutput(Integer.valueOf(line[2]));
                        answerList.put(Integer.valueOf(line[0]), answer);
                        break;
                    case "Options.csv":
                        Option option = new Option();
                        option.setName(line[1]);
                        option.setFactor(Float.valueOf(line[2]));
                        option.setAnswer(answerList.get(Integer.valueOf(line[3])));
                        optionList.put(Integer.valueOf(line[0]), option);
                        break;
                    case "CompositiveScores.csv":
                        CompositiveScore compositiveScore = new CompositiveScore();
                        compositiveScore.setCode(line[1]);
                        compositiveScore.setLabel(line[2]);
                        if (!line[3].equals("")) compositiveScore.setCompositive_score(compositiveScoreList.get(Integer.valueOf(line[3])));
                        compositiveScoreList.put(Integer.valueOf(line[0]), compositiveScore);
                        break;
                    case "Questions.csv":
                        Question question = new Question();
                        question.setCode(line[1]);
                        question.setDe_name(line[2]);
                        question.setShort_name(line[3]);
                        question.setForm_name(line[4]);
                        question.setUid(line[5]);
                        question.setOrder_pos(Integer.valueOf(line[6]));
                        question.setNumerator_w(Float.valueOf(line[7]));
                        question.setDenominator_w(Float.valueOf(line[8]));
                        question.setHeader(headerList.get(Integer.valueOf(line[9])));
                        question.setAnswer(answerList.get(Integer.valueOf(line[10])));
                        if (!line[11].equals(""))
                            question.setQuestion(questionList.get(Integer.valueOf(line[11])));
                        if (line.length == 13 && !line[12].equals("")) question.setCompositiveScore(compositiveScoreList.get(Integer.valueOf(line[12])));
                        questionList.put(Integer.valueOf(line[0]), question);
                        break;
/*                    case "HeadersCustom.csv":
                        Header headerCustom = new Header();
                        headerCustom.setShort_name(line[1]);
                        headerCustom.setName(line[2]);
                        headerCustom.setOrder_pos(Integer.valueOf(line[3]));
                        headerCustom.setTab(tabList.get(Integer.valueOf(line[4])));
                        headerCustomList.put(Integer.valueOf(line[0]), headerCustom);
                        break;
                    case "QuestionsCustom.csv":
                        Question questionCustom = new Question();
                        questionCustom.setCode(line[1]);
                        questionCustom.setDe_name(line[2]);
                        questionCustom.setShort_name(line[3]);
                        questionCustom.setForm_name(line[4]);
                        questionCustom.setUid(line[5]);
                        questionCustom.setOrder_pos(Integer.valueOf(line[6]));
                        questionCustom.setNumerator_w(Float.valueOf(line[7]));
                        questionCustom.setDenominator_w(Float.valueOf(line[8]));
                        questionCustom.setHeader(headerCustomList.get(Integer.valueOf(line[9])));
                        if (!line[10].equals("")) questionCustom.setAnswer(answerList.get(Integer.valueOf(line[10])));
                        if (!line[11].equals("")) questionCustom.setQuestion(questionCustomList.get(Integer.valueOf(line[11])));
                        questionCustomList.put(Integer.valueOf(line[0]), questionCustom);
                        break;*/
                }
            }
            reader.close();
        }

        Tab.saveInTx(tabList.values());
        Header.saveInTx(headerList.values());
        Answer.saveInTx(answerList.values());
        Option.saveInTx(optionList.values());
        CompositiveScore.saveInTx(compositiveScoreList.values());
        Question.saveInTx(questionList.values());


        //Header.saveInTx(headerCustomList.values());
        //Question.saveInTx(questionCustomList.values());

    }

    public static void populateDummyData(){
        OrgUnit orgUnit = new OrgUnit("DummyOrgUnit", "Dummy orgUnit");
        orgUnit.save();
        OrgUnit orgUnit2 = new OrgUnit("1234", "Clinical Center for District X");
        orgUnit.save();
        OrgUnit orgUnit3 = new OrgUnit("7634", "Clinical Center 23 for District Y");
        orgUnit2.save();

        Program program = new Program("DummyProgram", "Dummy program");
        program.save();

        Survey survey = new Survey(orgUnit, program, Session.getUser());
        survey.save();

        Survey survey2 = new Survey();
        survey2.setOrgUnit(orgUnit);
        survey2.setUser(Session.getUser());
        survey2.setEventDate("23 Mar 2015");
        survey2.save();

        Survey survey3 = new Survey();
        survey3.setOrgUnit(orgUnit2);
        survey3.setUser(Session.getUser());
        survey3.setEventDate("25 Mar 2015");
        survey3.save();
    }

}