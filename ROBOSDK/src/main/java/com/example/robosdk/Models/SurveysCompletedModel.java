package com.example.robosdk.Models;

public class SurveysCompletedModel {
    public String surveyTitle,
            surveySendDate,
            surveyID,
            surveySendID,
            respondDate,
            surveyLink;
    public int surveyPoints;

    public SurveysCompletedModel(String surveyTitle, String surveySendDate, String surveyID, String surveySendID, String respondDate, int surveyPoints, String surveyLink) {
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

    public String getSurveySendDate() {
        return surveySendDate;
    }

    public void setSurveySendDate(String surveySendDate) {
        this.surveySendDate = surveySendDate;
    }

    public String getRespondDate() {
        return respondDate;
    }

    public void setRespondDate(String respondDate) {
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
