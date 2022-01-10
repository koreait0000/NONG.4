package com.spring.nong4.api;

import com.spring.nong4.api.model.farmWorkingPlanDomain;
import com.spring.nong4.api.model.monthFarmTechDomain;
import com.spring.nong4.api.model.monthFarmTechDtlDomain;
import com.spring.nong4.board.model.SearchCriteria;
import com.spring.nong4.api.model.apiVideoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        model.addAllAttributes(service.monthFarmTechDtlImg(farmTechDomain, scri));
        model.addAllAttributes(service.monthFarmTech(farmTechDomain,scri));
        return "api/monthFarmTech";
    }

    @GetMapping("/monthFarmTechDtl")
    public String monthFarmTechDtl(monthFarmTechDtlDomain farmTechDtlDomain, Model model) {
        model.addAllAttributes(service.monthFarmTechDtl(farmTechDtlDomain));
        return "api/monthFarmTechDtl";
    }

    @GetMapping("/farmWorkingPlan")
    public String farmWorkingPlan(farmWorkingPlanDomain workingDomain, Model model, String ajaxValue) {
        model.addAllAttributes(service.farmWorkingPlan(workingDomain, ajaxValue));
        System.out.println("CONTROLLER_AJAX : " + workingDomain.getWorkingScheduleList());
        System.out.println("CONTROLLER_AJAXVLAUE : " + ajaxValue);
        if(ajaxValue == null) {
            ajaxValue = workingDomain.getWorkingItemList().get(0).getKidofcomdtySeCode();
        }
        model.addAllAttributes(service.workScheduleLst(workingDomain, ajaxValue));

        return "api/farmWorkingPlan";
    }

    @ResponseBody
    @PostMapping("/farmWorkingPlan")
    public Map<String, Object> workScheduletLst(@RequestBody String ajaxValue, farmWorkingPlanDomain workingPlanDomain, Model model) {
        ajaxValue = ajaxValue.replaceAll("\\\"","");
        System.out.println("ajaxValue : "  + ajaxValue);
        System.out.println("workingPlanDomain : " + workingPlanDomain);
//        service.workScheduleLst(workingPlanDomain);
//        map.put("workScheduleLst", workingDomain.getWorkingScheduleList());

        farmWorkingPlan(workingPlanDomain, model, ajaxValue);

        return service.workScheduleLst(workingPlanDomain, ajaxValue);
    }
}
