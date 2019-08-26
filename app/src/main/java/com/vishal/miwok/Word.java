package com.vishal.miwok;

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;
    private int mImageResourceId=NO_IMG;
    private static final int NO_IMG=-1;
    private int audio_id;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation,int number,int au_id){
        mImageResourceId=number;
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        audio_id=au_id;
    }
    public Word(String defaultTranslation, String miwokTranslation,int au_id){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        audio_id=au_id;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {

        return mMiwokTranslation;
    }
    public  int getmImageResourceId(){
        return mImageResourceId;
    }
    public  boolean hasimage(){
        return mImageResourceId !=NO_IMG;
    }

    public int getAudio_id() {
        return audio_id;
    }
}

