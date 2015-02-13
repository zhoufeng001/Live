package com.zf.live.robot;

/**
 * 消息工厂
 * @author zf
 *
 */
public class MessageFactory {


	/**
	 * 纯文字
	 */
	public static final int TextType_Text = 2 ;
	
	/**
	 * 普通表情
	 */
	public static final int TextType_CommonEm = 2 << 1 ;
	
	/**
	 * 文本消息
	 */
	private static final String [] textMessages = {
		"我过得很好。",
		"你在忙些什么",
		"怎么了？",
		"到目前为止，一切都好。",
		"嗨，好久不见了。",
		"今天是个好日子。",
		"有进展吗？",
		"请原谅我打断谈话加入进来",
		"多谢你与我分享",
		"哦，我明白了",
		"以后再谈",
		"你在那里吗?",
		"晚点回来",
		"随便说一下",
		"非常好"  
	};
	
	/**
	 * 普通表情
	 */
	private static final String commonEms[] = {
		"[em_1]",
		"[em_2]",
		"[em_3]",
		"[em_4]",
		"[em_5]",
		"[em_6]",
		"[em_7]",
		"[em_8]",
		"[em_9]",
		"[em_10]",
		"[em_11]",
		"[em_12]",
	};
	
	
	/**
	 * 创建一条消息
	 * @param msgType
	 * @return
	 */
	public static String buildMessage(int textType){
		StringBuilder text = new StringBuilder() ;
		if((textType & TextType_Text) == TextType_Text){
			text.append(getOneMessage(TextType_Text));
		}
		
		if((textType & TextType_CommonEm) == TextType_CommonEm){
			text.append(getOneMessage(TextType_CommonEm));
		}
		return text.toString() ;
	}
		
	
	private static String getOneMessage(int textType){
		if(textType == TextType_Text){
			int msgIdx = (int)(Math.random() * textMessages.length) ;
			return textMessages[msgIdx];
		}else if(textType == TextType_CommonEm){
			int msgIdx = (int)(Math.random() * commonEms.length) ;
			return commonEms[msgIdx];
		}
		return "";
	}

	
	public static void main(String[] args) {
		String message = buildMessage(MessageFactory.TextType_Text 
				| MessageFactory.TextType_CommonEm); 
		System.out.println(message);
	}
	
}
