package com.example.rnsdk.Models;


public class ContactData {
    public String contactID;

    public String firstName;
    public String lastName;
    public String mobilePhone;
    public String memberCardID;
    public String emailAddress;
    public String profilePitcure;
    public String contactListVisibleName;
    public String contactListName;
    public String contactListID;
    public String address;
    public String city;
    public String state;
    public String zipCode;
    public String gender;
    public String rootID;
    public String phone;
    public String emailFormat;
    public String confirmationStatus;
    public String activityStatus;
    public String customFiledsValue;
    public String birthDate;
    public String anniversary;
    public String lastVisitDate;
    public String familyMemberBDay;
    public String overrideTriggerID;
    public String createdBy;
    public String modifiedBy;
    public String createdDate;
    public String modifiedDate;
    public String subscribeKey;
    public String subscribeDate;
    public String additionalBirthDates;
    public String isAddToAutoresponder;
    public String preferredMediaType;
    public String referelContactID;
    public String userName;
    public String password;
    public String listJoinDate;
    public String fbUserId;
    public String fbUserAccessToken;
    public String fbTokenExpirationDate;
    public String fbMessageIndex;
    public String pointBalanceAtImport;
    public String rqp;
    public String vipLevel;
    public String contactLevelExpirationDigit;
    public String contactLevelExpirationWord;
    public String contactLevelExpirationTotal;
    public String vipMemberJoiningDate;
    public String vipLevelColour;
    public String sharedContactID;
    public String driverLicense;
    public String addressID;
    public String lastSentRotateNumber;
    public String smsConfirmationStatus;
    public String hasReferredFriendJoined;
    public String signUpWebform;
    public String isNoNeedToSetAutoresponder;
    public String languageID;
    public String rewardProgramIDNew;
    public String address2;
    public String address3;
    public String leaderBoardName;
    public String profileCompletionPoints;
    public String vistaPassword;
    public String gcmDeviceToken;
    public String deviceType;
    public String hubSpotId;
    public String amountSum;
    public String fullName;
    public String country;
    public String countryID;
    public String rewardProgramName;
    public String facebookReferralPoints;

    public Boolean isRequiredPasswordChanged;
    public Boolean isBeaconAppLogin;
    public boolean isActive;
    public boolean isDelete;
    public boolean isProfileComplete;
    public boolean isAllowEmail;
    public boolean isAllowSMS;
    public boolean isAllowPostalMail;
    public boolean isOverrideSent;
    public boolean isFBUser;
    public boolean isAllowFacebookBonusPoints;
    public boolean isThankYouEmailByWeForm;
    public boolean isImported;
    public boolean isAfterImportShowRPG;
    public boolean isVIPLevelExpired;
    public boolean isPrimaryContact;
    public boolean isAfterReferDoAnyTx;
    public boolean isBirthdayDayEdited;
    public boolean isAnniversaryEdited;
    public boolean isOptOut;
    public boolean isAddressVerify;
    public boolean isAllowPush;
    public boolean isMultiTieredRewardsType;
    public boolean isPaidVIPRewardsType;
    public boolean isAllowCheckInPoints;

    public double pointBalance;
    public double reedemablePoints;

    public double totalSpent;
    public double qualificationPoints;
    public int limeOptionStatus;
    public int signupType;
    public int contactListPointValue;
    public int txCount;



    public ContactData(String contactID, double pointBalance, double reedemablePoints, String firstName, String lastName, String mobilePhone, String memberCardID, String emailAddress, Boolean isBeaconAppLogin, String profilePitcure, Boolean isRequiredPasswordChanged, String contactListVisibleName, String contactListName, String contactListID, String address, String city, String state, String zipCode, String gender, String rootID, String phone, String emailFormat, String confirmationStatus, String activityStatus, String customFiledsValue, String birthDate, String anniversary, double totalSpent, String lastVisitDate, String familyMemberBDay, String overrideTriggerID, boolean isActive, boolean isDelete, String createdBy, String modifiedBy, String createdDate, String modifiedDate, String subscribeKey, String subscribeDate, boolean isProfileComplete, String additionalBirthDates, String isAddToAutoresponder, boolean isAllowEmail, boolean isAllowSMS, boolean isAllowPostalMail, String preferredMediaType, String referelContactID, boolean isOverrideSent, boolean isFBUser, String userName, String password, String listJoinDate, double qualificationPoints, boolean isAllowFacebookBonusPoints, String fbUserId, String fbUserAccessToken, String fbTokenExpirationDate, String fbMessageIndex, boolean isThankYouEmailByWeForm, boolean isImported, boolean isAfterImportShowRPG, String pointBalanceAtImport, String rqp, String vipLevel, String contactLevelExpirationDigit, String contactLevelExpirationWord, String contactLevelExpirationTotal, String vipMemberJoiningDate, boolean isVIPLevelExpired, String sharedContactID, boolean isPrimaryContact, boolean isAfterReferDoAnyTx, String vipLevelColour, String driverLicense, String addressID, String lastSentRotateNumber, String smsConfirmationStatus, int limeOptionStatus, String hasReferredFriendJoined, boolean isBirthdayDayEdited, boolean isAnniversaryEdited, String isNoNeedToSetAutoresponder, int signupType, String signUpWebform, boolean isOptOut, String languageID, String rewardProgramIDNew, String address2, String address3, String leaderBoardName, String profileCompletionPoints, boolean isAddressVerify, String vistaPassword, boolean isAllowPush, String gcmDeviceToken, String deviceType, String hubSpotId, String amountSum, String fullName, String country, String countryID, int contactListPointValue, String rewardProgramName, boolean isMultiTieredRewardsType, boolean isPaidVIPRewardsType, boolean isAllowCheckInPoints, String facebookReferralPoints, int txCount) {
        this.contactID = contactID;
        this.pointBalance = pointBalance;
        this.reedemablePoints = reedemablePoints;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.memberCardID = memberCardID;
        this.emailAddress = emailAddress;
        this.isBeaconAppLogin = isBeaconAppLogin;
        this.profilePitcure = profilePitcure;
        this.isRequiredPasswordChanged = isRequiredPasswordChanged;
        this.contactListVisibleName = contactListVisibleName;
        this.contactListName = contactListName;
        this.contactListID = contactListID;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.gender = gender;
        this.rootID = rootID;
        this.phone = phone;
        this.emailFormat = emailFormat;
        this.confirmationStatus = confirmationStatus;
        this.activityStatus = activityStatus;
        this.customFiledsValue = customFiledsValue;
        this.birthDate = birthDate;
        this.anniversary = anniversary;
        this.totalSpent = totalSpent;
        this.lastVisitDate = lastVisitDate;
        this.familyMemberBDay = familyMemberBDay;
        this.overrideTriggerID = overrideTriggerID;
        this.isActive = isActive;
        this.isDelete = isDelete;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.subscribeKey = subscribeKey;
        this.subscribeDate = subscribeDate;
        this.isProfileComplete = isProfileComplete;
        this.additionalBirthDates = additionalBirthDates;
        this.isAddToAutoresponder = isAddToAutoresponder;
        this.isAllowEmail = isAllowEmail;
        this.isAllowSMS = isAllowSMS;
        this.isAllowPostalMail = isAllowPostalMail;
        this.preferredMediaType = preferredMediaType;
        this.referelContactID = referelContactID;
        this.isOverrideSent = isOverrideSent;
        this.isFBUser = isFBUser;
        this.userName = userName;
        this.password = password;
        this.listJoinDate = listJoinDate;
        this.qualificationPoints = qualificationPoints;
        this.isAllowFacebookBonusPoints = isAllowFacebookBonusPoints;
        this.fbUserId = fbUserId;
        this.fbUserAccessToken = fbUserAccessToken;
        this.fbTokenExpirationDate = fbTokenExpirationDate;
        this.fbMessageIndex = fbMessageIndex;
        this.isThankYouEmailByWeForm = isThankYouEmailByWeForm;
        this.isImported = isImported;
        this.isAfterImportShowRPG = isAfterImportShowRPG;
        this.pointBalanceAtImport = pointBalanceAtImport;
        this.rqp = rqp;
        this.vipLevel = vipLevel;
        this.contactLevelExpirationDigit = contactLevelExpirationDigit;
        this.contactLevelExpirationWord = contactLevelExpirationWord;
        this.contactLevelExpirationTotal = contactLevelExpirationTotal;
        this.vipMemberJoiningDate = vipMemberJoiningDate;
        this.isVIPLevelExpired = isVIPLevelExpired;
        this.sharedContactID = sharedContactID;
        this.isPrimaryContact = isPrimaryContact;
        this.isAfterReferDoAnyTx = isAfterReferDoAnyTx;
        this.vipLevelColour = vipLevelColour;
        this.driverLicense = driverLicense;
        this.addressID = addressID;
        this.lastSentRotateNumber = lastSentRotateNumber;
        this.smsConfirmationStatus = smsConfirmationStatus;
        this.limeOptionStatus = limeOptionStatus;
        this.hasReferredFriendJoined = hasReferredFriendJoined;
        this.isBirthdayDayEdited = isBirthdayDayEdited;
        this.isAnniversaryEdited = isAnniversaryEdited;
        this.isNoNeedToSetAutoresponder = isNoNeedToSetAutoresponder;
        this.signupType = signupType;
        this.signUpWebform = signUpWebform;
        this.isOptOut = isOptOut;
        this.languageID = languageID;
        this.rewardProgramIDNew = rewardProgramIDNew;
        this.address2 = address2;
        this.address3 = address3;
        this.leaderBoardName = leaderBoardName;
        this.profileCompletionPoints = profileCompletionPoints;
        this.isAddressVerify = isAddressVerify;
        this.vistaPassword = vistaPassword;
        this.isAllowPush = isAllowPush;
        this.gcmDeviceToken = gcmDeviceToken;
        this.deviceType = deviceType;
        this.hubSpotId = hubSpotId;
        this.amountSum = amountSum;
        this.fullName = fullName;
        this.country = country;
        this.countryID = countryID;
        this.contactListPointValue = contactListPointValue;
        this.rewardProgramName = rewardProgramName;
        this.isMultiTieredRewardsType = isMultiTieredRewardsType;
        this.isPaidVIPRewardsType = isPaidVIPRewardsType;
        this.isAllowCheckInPoints = isAllowCheckInPoints;
        this.facebookReferralPoints = facebookReferralPoints;
        this.txCount = txCount;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public double getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(double pointBalance) {
        this.pointBalance = pointBalance;
    }

    public double getReedemablePoints() {
        return reedemablePoints;
    }

    public void setReedemablePoints(double reedemablePoints) {
        this.reedemablePoints = reedemablePoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMemberCardID() {
        return memberCardID;
    }

    public void setMemberCardID(String memberCardID) {
        this.memberCardID = memberCardID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getBeaconAppLogin() {
        return isBeaconAppLogin;
    }

    public void setBeaconAppLogin(Boolean beaconAppLogin) {
        isBeaconAppLogin = beaconAppLogin;
    }

    public String getProfilePitcure() {
        return profilePitcure;
    }

    public void setProfilePitcure(String profilePitcure) {
        this.profilePitcure = profilePitcure;
    }

    public Boolean getRequiredPasswordChanged() {
        return isRequiredPasswordChanged;
    }

    public void setRequiredPasswordChanged(Boolean requiredPasswordChanged) {
        isRequiredPasswordChanged = requiredPasswordChanged;
    }

    public String getContactListVisibleName() {
        return contactListVisibleName;
    }

    public void setContactListVisibleName(String contactListVisibleName) {
        this.contactListVisibleName = contactListVisibleName;
    }

    public String getContactListName() {
        return contactListName;
    }

    public void setContactListName(String contactListName) {
        this.contactListName = contactListName;
    }

    public String getContactListID() {
        return contactListID;
    }

    public void setContactListID(String contactListID) {
        this.contactListID = contactListID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRootID() {
        return rootID;
    }

    public void setRootID(String rootID) {
        this.rootID = rootID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailFormat() {
        return emailFormat;
    }

    public void setEmailFormat(String emailFormat) {
        this.emailFormat = emailFormat;
    }

    public String getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getCustomFiledsValue() {
        return customFiledsValue;
    }

    public void setCustomFiledsValue(String customFiledsValue) {
        this.customFiledsValue = customFiledsValue;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(String anniversary) {
        this.anniversary = anniversary;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getFamilyMemberBDay() {
        return familyMemberBDay;
    }

    public void setFamilyMemberBDay(String familyMemberBDay) {
        this.familyMemberBDay = familyMemberBDay;
    }

    public String getOverrideTriggerID() {
        return overrideTriggerID;
    }

    public void setOverrideTriggerID(String overrideTriggerID) {
        this.overrideTriggerID = overrideTriggerID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSubscribeKey() {
        return subscribeKey;
    }

    public void setSubscribeKey(String subscribeKey) {
        this.subscribeKey = subscribeKey;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public boolean isProfileComplete() {
        return isProfileComplete;
    }

    public void setProfileComplete(boolean profileComplete) {
        isProfileComplete = profileComplete;
    }

    public String getAdditionalBirthDates() {
        return additionalBirthDates;
    }

    public void setAdditionalBirthDates(String additionalBirthDates) {
        this.additionalBirthDates = additionalBirthDates;
    }

    public String getIsAddToAutoresponder() {
        return isAddToAutoresponder;
    }

    public void setIsAddToAutoresponder(String isAddToAutoresponder) {
        this.isAddToAutoresponder = isAddToAutoresponder;
    }

    public boolean isAllowEmail() {
        return isAllowEmail;
    }

    public void setAllowEmail(boolean allowEmail) {
        isAllowEmail = allowEmail;
    }

    public boolean isAllowSMS() {
        return isAllowSMS;
    }

    public void setAllowSMS(boolean allowSMS) {
        isAllowSMS = allowSMS;
    }

    public boolean isAllowPostalMail() {
        return isAllowPostalMail;
    }

    public void setAllowPostalMail(boolean allowPostalMail) {
        isAllowPostalMail = allowPostalMail;
    }

    public String getPreferredMediaType() {
        return preferredMediaType;
    }

    public void setPreferredMediaType(String preferredMediaType) {
        this.preferredMediaType = preferredMediaType;
    }

    public String getReferelContactID() {
        return referelContactID;
    }

    public void setReferelContactID(String referelContactID) {
        this.referelContactID = referelContactID;
    }

    public boolean isOverrideSent() {
        return isOverrideSent;
    }

    public void setOverrideSent(boolean overrideSent) {
        isOverrideSent = overrideSent;
    }

    public boolean isFBUser() {
        return isFBUser;
    }

    public void setFBUser(boolean FBUser) {
        isFBUser = FBUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getListJoinDate() {
        return listJoinDate;
    }

    public void setListJoinDate(String listJoinDate) {
        this.listJoinDate = listJoinDate;
    }

    public double getQualificationPoints() {
        return qualificationPoints;
    }

    public void setQualificationPoints(double qualificationPoints) {
        this.qualificationPoints = qualificationPoints;
    }

    public boolean isAllowFacebookBonusPoints() {
        return isAllowFacebookBonusPoints;
    }

    public void setAllowFacebookBonusPoints(boolean allowFacebookBonusPoints) {
        isAllowFacebookBonusPoints = allowFacebookBonusPoints;
    }

    public String getFbUserId() {
        return fbUserId;
    }

    public void setFbUserId(String fbUserId) {
        this.fbUserId = fbUserId;
    }

    public String getFbUserAccessToken() {
        return fbUserAccessToken;
    }

    public void setFbUserAccessToken(String fbUserAccessToken) {
        this.fbUserAccessToken = fbUserAccessToken;
    }

    public String getFbTokenExpirationDate() {
        return fbTokenExpirationDate;
    }

    public void setFbTokenExpirationDate(String fbTokenExpirationDate) {
        this.fbTokenExpirationDate = fbTokenExpirationDate;
    }

    public String getFbMessageIndex() {
        return fbMessageIndex;
    }

    public void setFbMessageIndex(String fbMessageIndex) {
        this.fbMessageIndex = fbMessageIndex;
    }

    public boolean isThankYouEmailByWeForm() {
        return isThankYouEmailByWeForm;
    }

    public void setThankYouEmailByWeForm(boolean thankYouEmailByWeForm) {
        isThankYouEmailByWeForm = thankYouEmailByWeForm;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public boolean isAfterImportShowRPG() {
        return isAfterImportShowRPG;
    }

    public void setAfterImportShowRPG(boolean afterImportShowRPG) {
        isAfterImportShowRPG = afterImportShowRPG;
    }

    public String getPointBalanceAtImport() {
        return pointBalanceAtImport;
    }

    public void setPointBalanceAtImport(String pointBalanceAtImport) {
        this.pointBalanceAtImport = pointBalanceAtImport;
    }

    public String getRqp() {
        return rqp;
    }

    public void setRqp(String rqp) {
        this.rqp = rqp;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getContactLevelExpirationDigit() {
        return contactLevelExpirationDigit;
    }

    public void setContactLevelExpirationDigit(String contactLevelExpirationDigit) {
        this.contactLevelExpirationDigit = contactLevelExpirationDigit;
    }

    public String getContactLevelExpirationWord() {
        return contactLevelExpirationWord;
    }

    public void setContactLevelExpirationWord(String contactLevelExpirationWord) {
        this.contactLevelExpirationWord = contactLevelExpirationWord;
    }

    public String getContactLevelExpirationTotal() {
        return contactLevelExpirationTotal;
    }

    public void setContactLevelExpirationTotal(String contactLevelExpirationTotal) {
        this.contactLevelExpirationTotal = contactLevelExpirationTotal;
    }

    public String getVipMemberJoiningDate() {
        return vipMemberJoiningDate;
    }

    public void setVipMemberJoiningDate(String vipMemberJoiningDate) {
        this.vipMemberJoiningDate = vipMemberJoiningDate;
    }

    public boolean isVIPLevelExpired() {
        return isVIPLevelExpired;
    }

    public void setVIPLevelExpired(boolean VIPLevelExpired) {
        isVIPLevelExpired = VIPLevelExpired;
    }

    public String getSharedContactID() {
        return sharedContactID;
    }

    public void setSharedContactID(String sharedContactID) {
        this.sharedContactID = sharedContactID;
    }

    public boolean isPrimaryContact() {
        return isPrimaryContact;
    }

    public void setPrimaryContact(boolean primaryContact) {
        isPrimaryContact = primaryContact;
    }

    public boolean isAfterReferDoAnyTx() {
        return isAfterReferDoAnyTx;
    }

    public void setAfterReferDoAnyTx(boolean afterReferDoAnyTx) {
        isAfterReferDoAnyTx = afterReferDoAnyTx;
    }

    public String getVipLevelColour() {
        return vipLevelColour;
    }

    public void setVipLevelColour(String vipLevelColour) {
        this.vipLevelColour = vipLevelColour;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getLastSentRotateNumber() {
        return lastSentRotateNumber;
    }

    public void setLastSentRotateNumber(String lastSentRotateNumber) {
        this.lastSentRotateNumber = lastSentRotateNumber;
    }

    public String getSmsConfirmationStatus() {
        return smsConfirmationStatus;
    }

    public void setSmsConfirmationStatus(String smsConfirmationStatus) {
        this.smsConfirmationStatus = smsConfirmationStatus;
    }

    public int getLimeOptionStatus() {
        return limeOptionStatus;
    }

    public void setLimeOptionStatus(int limeOptionStatus) {
        this.limeOptionStatus = limeOptionStatus;
    }

    public String getHasReferredFriendJoined() {
        return hasReferredFriendJoined;
    }

    public void setHasReferredFriendJoined(String hasReferredFriendJoined) {
        this.hasReferredFriendJoined = hasReferredFriendJoined;
    }

    public boolean isBirthdayDayEdited() {
        return isBirthdayDayEdited;
    }

    public void setBirthdayDayEdited(boolean birthdayDayEdited) {
        isBirthdayDayEdited = birthdayDayEdited;
    }

    public boolean isAnniversaryEdited() {
        return isAnniversaryEdited;
    }

    public void setAnniversaryEdited(boolean anniversaryEdited) {
        isAnniversaryEdited = anniversaryEdited;
    }

    public String getIsNoNeedToSetAutoresponder() {
        return isNoNeedToSetAutoresponder;
    }

    public void setIsNoNeedToSetAutoresponder(String isNoNeedToSetAutoresponder) {
        this.isNoNeedToSetAutoresponder = isNoNeedToSetAutoresponder;
    }

    public int getSignupType() {
        return signupType;
    }

    public void setSignupType(int signupType) {
        this.signupType = signupType;
    }

    public String getSignUpWebform() {
        return signUpWebform;
    }

    public void setSignUpWebform(String signUpWebform) {
        this.signUpWebform = signUpWebform;
    }

    public boolean isOptOut() {
        return isOptOut;
    }

    public void setOptOut(boolean optOut) {
        isOptOut = optOut;
    }

    public String getLanguageID() {
        return languageID;
    }

    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }

    public String getRewardProgramIDNew() {
        return rewardProgramIDNew;
    }

    public void setRewardProgramIDNew(String rewardProgramIDNew) {
        this.rewardProgramIDNew = rewardProgramIDNew;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getLeaderBoardName() {
        return leaderBoardName;
    }

    public void setLeaderBoardName(String leaderBoardName) {
        this.leaderBoardName = leaderBoardName;
    }

    public String getProfileCompletionPoints() {
        return profileCompletionPoints;
    }

    public void setProfileCompletionPoints(String profileCompletionPoints) {
        this.profileCompletionPoints = profileCompletionPoints;
    }

    public boolean isAddressVerify() {
        return isAddressVerify;
    }

    public void setAddressVerify(boolean addressVerify) {
        isAddressVerify = addressVerify;
    }

    public String getVistaPassword() {
        return vistaPassword;
    }

    public void setVistaPassword(String vistaPassword) {
        this.vistaPassword = vistaPassword;
    }

    public boolean isAllowPush() {
        return isAllowPush;
    }

    public void setAllowPush(boolean allowPush) {
        isAllowPush = allowPush;
    }

    public String getGcmDeviceToken() {
        return gcmDeviceToken;
    }

    public void setGcmDeviceToken(String gcmDeviceToken) {
        this.gcmDeviceToken = gcmDeviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getHubSpotId() {
        return hubSpotId;
    }

    public void setHubSpotId(String hubSpotId) {
        this.hubSpotId = hubSpotId;
    }

    public String getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(String amountSum) {
        this.amountSum = amountSum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public int getContactListPointValue() {
        return contactListPointValue;
    }

    public void setContactListPointValue(int contactListPointValue) {
        this.contactListPointValue = contactListPointValue;
    }

    public String getRewardProgramName() {
        return rewardProgramName;
    }

    public void setRewardProgramName(String rewardProgramName) {
        this.rewardProgramName = rewardProgramName;
    }

    public boolean isMultiTieredRewardsType() {
        return isMultiTieredRewardsType;
    }

    public void setMultiTieredRewardsType(boolean multiTieredRewardsType) {
        isMultiTieredRewardsType = multiTieredRewardsType;
    }

    public boolean isPaidVIPRewardsType() {
        return isPaidVIPRewardsType;
    }

    public void setPaidVIPRewardsType(boolean paidVIPRewardsType) {
        isPaidVIPRewardsType = paidVIPRewardsType;
    }

    public boolean isAllowCheckInPoints() {
        return isAllowCheckInPoints;
    }

    public void setAllowCheckInPoints(boolean allowCheckInPoints) {
        isAllowCheckInPoints = allowCheckInPoints;
    }

    public String getFacebookReferralPoints() {
        return facebookReferralPoints;
    }

    public void setFacebookReferralPoints(String facebookReferralPoints) {
        this.facebookReferralPoints = facebookReferralPoints;
    }

    public int getTxCount() {
        return txCount;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }
}