package com.bicycle.zrl.controller;

import com.bicycle.zrl.entity.Bick;
import com.bicycle.zrl.service.BickService;
import com.bicycle.zrl.util.HttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bick")
public class BickController {

	@Autowired
	private BickService bickService;

    /**
     * 新增单车
     * @param bick
     * @return
     */
	@RequestMapping("/addBick")
	public String addBick(@RequestBody Bick bick) {
		bickService.addBick(bick);
		return "success";
	}

    /**
     * 查找附件的单车
     */
    @RequestMapping("/findNear")
    @ResponseBody
    public List<GeoResult<Bick>> findNear(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = HttpHelper.paramRequest(request);
        return bickService.findNear(Double.parseDouble(map.get("longitude").toString()),Double.parseDouble(map.get("latitude").toString()));
    }


	
}
