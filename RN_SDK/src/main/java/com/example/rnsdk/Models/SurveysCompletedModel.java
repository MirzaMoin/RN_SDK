package com.example.rnsdk.Models;

import java.util.Date;

public class SurveysCompletedModel {
    public String surveyTitle;
    public Date surveySendDate;
    public String surveyID;
    public String surveySendID;
    public Date respondDate;
    public int surveyPoints;
    public String surveyLink;

    public SurveysCompletedModel(String surveyTitle, Date surveySendDate, String surveyID, String surveySendID, Date respondDate, int surveyPoints, String surveyLink) {
        this.surveyTitle = surveyTitle;
        this.surveySendDate = surveySendDate;
        this.surveyID = surveyID;
        this.surveySendID = surveySendID;
        this.respondDate = respondDate;
        this.surveyPoints = surveyPoints;
        this.surveyLink = surveyLink;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public Date getSurveySendDate() {
        return surveySendDate;
    }

    public void setSurveySendDate(Date surveySendDate) {
        this.surveySendDate = surveySendDate;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getSurveySendID() {
        return surveySendID;
    }

    public void setSurveySendID(String surveySendID) {
        this.surveySendID = surveySendID;
    }

    public Date getRespondDate() {
        return respondDate;
    }

    public void setRespondDate(Date respondDate) {
        this.respondDate = respondDate;
    }

    public int getSurveyPoints() {
        return surveyPoints;
    }

    public void setSurveyPoints(int surveyPoints) {
        this.surveyPoints = surveyPoints;
    }

    public String getSurveyLink() {
        return surveyLink;
    }

    public void setSurveyLink(String surveyLink) {
        this.surveyLink = surveyLink;
    }
}
