package com.rechinx.pixiv_e.model;

/**
 * Created by Chin on 2016/7/17.
 */
public class WorksModel {

    public int rank;

    public int previous_rank;

    public WorkModel work;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPrevious_rank() {
        return previous_rank;
    }

    public void setPrevious_rank(int previous_rank) {
        this.previous_rank = previous_rank;
    }

    public WorkModel getWork() {
        return work;
    }

    public void setWork(WorkModel work) {
        this.work = work;
    }
}
