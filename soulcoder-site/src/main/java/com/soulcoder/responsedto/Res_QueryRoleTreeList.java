package com.soulcoder.responsedto;

import com.soulcoder.responsedto.dtomodel.QueryRoleTreeDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aministrator on 2018-01-25.
 */
public class Res_QueryRoleTreeList extends  ResponseBase {



    public List<QueryRoleTreeDetail> rolelist;

    public Res_QueryRoleTreeList(){
        rolelist = new ArrayList<QueryRoleTreeDetail>();
    }
}
