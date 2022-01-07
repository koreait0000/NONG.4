package com.spring.nong4.api;

import com.spring.nong4.api.model.monthFarmTechDomain;
import com.spring.nong4.api.model.monthFarmTechDtlDomain;
import com.spring.nong4.board.model.PageMaker;
import com.spring.nong4.board.model.SearchCriteria;
import com.spring.nong4.api.model.apiVideoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired private ApiService service;

    @GetMapping("/apiVideo")
    public String apiVideo (apiVideoDomain apiVideoDomain, Model model, SearchCriteria scri) {
        model.addAllAttributes(service.apiVideo(apiVideoDomain, scri));
        return "api/apiVideo";
    }

    @GetMapping("/monthFarmTech")
    public String monthFarmTech(monthFarmTechDomain farmTechDomain, Model model, SearchCriteria scri) {
        model.addAllAttributes(service.monthFarmTech(farmTechDomain,scri));
        return "api/monthFarmTech";
    }

    @GetMapping("/monthFarmTechDtl")
    public String monthFarmTechDtl(monthFarmTechDtlDomain farmTechDtlDomain, Model model) {
        model.addAllAttributes(service.monthFarmTechDtl(farmTechDtlDomain));
        return "api/monthFarmTechDtl";
    }

}
