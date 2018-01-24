package com.soulcoder.partener.gt;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * GeetestWeb闁板秶鐤嗛弬鍥︽
 */
public class GeetestConfig {

	// 婵夘偄鍙嗛懛顏勭箒閻ㄥ垻aptcha_id閸滃rivate_key
	private static final String geetest_id = "386d0be023e4e35c574147be5916b0c2";
	private static final String geetest_key = "fb080b49aa576d150bde6b2aff31201d";
	private static final boolean newfailback = true;

    /**
     * Gets geetest id.
     *
     * @return the geetest id
     */
    public static final String getGeetest_id() {
		return geetest_id;
	}

    /**
     * Gets geetest key.
     *
     * @return the geetest key
     */
    public static final String getGeetest_key() {
		return geetest_key;
	}

    /**
     * Isnewfailback boolean.
     *
     * @return the boolean
     */
    public static final boolean isnewfailback() {
		return newfailback;
	}

}
