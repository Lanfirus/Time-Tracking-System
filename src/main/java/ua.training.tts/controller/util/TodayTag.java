package ua.training.tts.controller.util;

import ua.training.tts.constant.ReqSesParameters;

import javax.servlet.jsp.tagext.TagSupport;
import java.time.LocalDate;

public class TodayTag extends TagSupport{

    public int doStartTag(){

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        pageContext.setAttribute(ReqSesParameters.TODAY, localDate);

        return SKIP_BODY;
    }
}
