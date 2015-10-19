package org.eyeseetea.malariacare.database.model;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import org.eyeseetea.malariacare.database.AppDatabase;
import org.eyeseetea.malariacare.database.utils.Session;
import org.eyeseetea.malariacare.utils.Constants;

import java.util.List;

@Table(databaseName = AppDatabase.NAME)
public class Tab extends SugarRecord<Tab> {

    @Column
    @PrimaryKey(autoincrement = true)
    long id_tab;

    @Column
    String name;

    @Column
    Integer order_pos;

    @Column
    Program program;

    @Column
    Integer type;

    public Tab() {
    }

    public Tab(String name, Integer order_pos, Program program, Integer type) {
        this.name = name;
        this.order_pos = order_pos;
        this.program = program;
        this.type = type;
    }

    public Long getId_tab() {
        return id_tab;
    }

    public void setId_tab(Long id_tab) {
        this.id_tab = id_tab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder_pos() {
        return order_pos;
    }

    public void setOrder_pos(Integer order_pos) {
        this.order_pos = order_pos;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Header> getHeaders(){
        return null;
        //TODO
//        return new Select().from(Header.class)
//                .where(Condition.column(Header$Table.TAB_ID_TAB).eq(this.getId_tab()))
//                .orderBy(Header$Table.ORDER_POS).queryList();
    }

    public List<Score> getScores(){
        return null;
        //TODO
//        return new Select().from(Score.class)
//                .where(Condition.column(Score$Table.TAB_ID_TAB).eq(this.getId_tab())).queryList();
    }

    /*
     * Return tabs filter by program and order by orderpos field
     */
    public static List<Tab> getTabsBySession(){
        return null;
        //TODO
//        return new Select().from(Tab.class)
//                .where(Condition.column(Tab$Table.TABGROUP_ID_TAB_GROUP).eq(Session.getSurvey().getTabGroup().getId_tab_group()))
//                .orderBy(Tab$Table.ORDER_POS).queryList();
    }

    /**
     * Checks if this tab is a general score tab.
     * @return
     */
    public boolean isGeneralScore(){
        return getType() == Constants.TAB_SCORE_SUMMARY && !isCompositeScore();
    }

    /**
     * Checks if this tab is the composite score tab
     * @return
     */
    public boolean isCompositeScore(){
        return getName().equals(Constants.COMPOSITE_SCORE_TAB_NAME);
    }

    /**
     * Checks if this tab is the adherence tab
     * @return
     */
    public boolean isAdherenceTab(){
        return getType() == Constants.TAB_ADHERENCE;
    }

    /**
     * Checks if this tab is the IQA tab
     * @return
     */
    public boolean isIQATab(){
        return getType() == Constants.TAB_IQATAB;
    }

    /**
     * Checks if this tab is a dynamic tab (sort of a wizard)
     * @return
     */
    public boolean isDynamicTab(){
        return getType() == Constants.TAB_DYNAMIC_AUTOMATIC_TAB;
    }

    @Override
    public String toString() {
        return "Tab{" +
                "name='" + name + '\'' +
                ", order_pos=" + order_pos +
                ", program=" + program +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tab)) return false;

        Tab tab = (Tab) o;

        if (!name.equals(tab.name)) return false;
        if (!order_pos.equals(tab.order_pos)) return false;
        if (!program.equals(tab.program)) return false;
        if (!type.equals(tab.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + order_pos.hashCode();
        result = 31 * result + program.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
