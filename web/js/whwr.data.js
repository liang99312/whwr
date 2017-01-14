var kq_A01List={"sz":[],"xh":-1};
var kq_A01ListBh={"sz":[],"xh":-1};
var lp_CangKuList={"sz":[],"xh":-1};
var lp_GangWeiList={"sz":[],"xh":-1};
var lp_GongXuFenLeiList={"sz":[],"xh":-1};
var lp_GongXuList={"sz":[],"xh":-1};
var lp_GongXuMoBanList={"sz":[],"xh":-1};
var lp_GongYingShangList={"sz":[],"xh":-1};
var lp_KeGuanCangKuList={"sz":[],"xh":-1};
var lp_KeHuList={"sz":[],"xh":-1};
var lp_WuZiZiDianList={"sz":[],"xh":-1};
var lp_WuZiLeiBieList={"sz":[],"xh":-1};
var lp_XuQiuLeiBieList={"sz":[],"xh":-1};
var lp_XuQiuBiaoZhunList={"sz":[],"xh":-1};
var lp_XuQiuList={"sz":[],"xh":-1};
var lp_BuMenList={"sz":[],"xh":-1};
var lp_YuanGongList={"sz":[],"xh":-1};
var lp_ZiDianLeiBieList={"sz":[],"xh":-1};
var lp_ZiDianList={};
var lp_QuanXian={"sz":[{"bh":"100000","mc":"人员管理","sz":[{"bh":"100100","mc":"人员管理"}]},
                       {"bh":"800000","mc":"部门管理","sz":[{"bh":"800100","mc":"部门管理"}]},
                       {"bh":"500000","mc":"客户管理","sz":[{"bh":"500100","mc":"客户管理"}]},
                       {"bh":"600000","mc":"供应商管理","sz":[{"bh":"600100","mc":"供应商管理"}]},
                       {"bh":"200000","mc":"仓库设置","sz":[{"bh":"200100","mc":"仓库管理"},{"bh":"200200","mc":"物资类别"},{"bh":"200300","mc":"物资字典"}]},
                       {"bh":"300000","mc":"仓库管理","sz":[{"bh":"300100","mc":"请购管理"},{"bh":"300200","mc":"采购管理"},{"bh":"300300","mc":"入库管理"},
                                                           {"bh":"300400","mc":"出库管理"},{"bh":"300500","mc":"发货管理"},{"bh":"300600","mc":"损耗管理"},
                                                           {"bh":"300700","mc":"还库管理"},{"bh":"300800","mc":"库存管理"},{"bh":"300900","mc":"统计分析"}]},
                       {"bh":"400000","mc":"报表设置","sz":[{"bh":"400100","mc":"报表管理"}]},
                       {"bh":"700000","mc":"企业字典","sz":[{"bh":"700100","mc":"字典类别"},{"bh":"700200","mc":"企业字典"}]},
                       {"bh":"900000","mc":"系统设置","sz":[{"bh":"900100","mc":"修改密码"}]}]};
var curList;
//var curEventObjId;
var preEventObjData;

//事件
function setEvent(){
	
}

/********************************查询函数*********************************/
function lp_getData(url, upData, dataObj, funName){
	var s = $.ajax({
		url: url,
		data: upData,
		dataType: "json",
		type: "post",
		cache: false,
		error: function(msg, textStatus){ failureResp(msg.responseText); },
		success:function(json){ if(checkResult(json)){ lp_setData(dataObj, json, funName); } }
	});
}

function lp_setData(dataObj, json, funName){
	dataObj.sz=json.sz;
	dataObj.xh=-1;
	if ((funName!=null)&& (funName!=undefined)) funName(json);
}

function lp_cxA01(funName){
	var field = "mc";
	var json = {"jsonObj":"{type:"+field+"}"};
	lp_getData("/whwr/getAllA01s.action", json, kq_A01List, funName);
}

function lp_cxA01Bh(funName){
	var field = "a0190";
	var json = {"jsonObj":"{type:"+field+"}"};
	lp_getData("/whwr/getAllA01s.action", json, kq_A01ListBh, funName);
}

//查询仓库
function lp_cxCangKu(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/ck/cxCk.do", json, lp_CangKuList, funName);
}

//查询组织工序
function lp_cxGangWei(fenLei, funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/qy/cxGw.do", json, lp_GangWeiList, funName);
}

//查询组织工序
function lp_cxGongXu(fenLei, funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxGx.do", json, lp_GongXuList, funName);
}

//查询工序分类
function lp_cxGongXuFenLei(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxGxfl.do", json, lp_GongXuFenLeiList, funName);
}

//查询工序模板
function lp_cxGongXuMoBan(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxGxmb.do", json, lp_GongXuMoBanList, funName);
}

//查询供应商
function lp_cxGongYingShang(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/whwr/cxGys.do", json, lp_GongYingShangList, funName);
}

//查询我可管的仓库
function lp_cxKeGuanCangKu(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/whwr/cxKgck.do", json, lp_KeGuanCangKuList, funName);
}

//查询客户
function lp_cxKeHu(keHuMingCheng, funName){
	var json = {"khmc":keHuMingCheng};
	json={"jsonObj":JSON.stringify(json)};
	lp_getData("/kh/cxKh.do", json, lp_KeHuList, funName);
}

//查询物资类别
function lp_cxWuZiLeiBie(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/whwr/cxWzlb.do", json, lp_WuZiLeiBieList, funName);
}

//查询物资字典
function lp_cxWuZiZiDian(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/ck/cxWzzd.do", json, lp_WuZiZiDianList, funName);
}

//查询需求
function lp_cxXuQiu(xuQiuMingCheng, funName){
	var json = {"mc":xuQiuMingCheng};
	json = {"jsonObj":JSON.stringify(json)};
	lp_getData("/zn/cxXq.do", json, lp_XuQiuList, funName);
}

//查询需求类别
function lp_cxXuQiuLeiBie(funName, dataObj){
	var data = dataObj ? dataObj : lp_XuQiuLeiBieList;
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxXqlb.do", json, data, funName);
}

//查询需求模板
function lp_cxXuQiuMoBan(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/zn/cxXqmb.do", json, lp_XuQiuBiaoZhunList, funName);
}

//查询员工
function lp_cxYuanGong(funName, xingMing, dataObj){
	var json = {"xm":xingMing};
	json={"jsonObj":JSON.stringify(json)};
	lp_getData("/whwr/getAllA01s.do", json, lp_YuanGongList, funName);
}

//查询组织字典
function lp_cxQiYeZiDian(fenLei, funName){
	lp_ZiDianList[fenLei]={"sz":[],"xh":-1};
	var json = {"flbh":fenLei};
	json = {"jsonObj":JSON.stringify(json)};
	lp_getData("/zn/cxZzzd.do", json, lp_ZiDianList[fenLei], funName);
}

//查询字典分类
function lp_cxZiDianLeiBie(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/whwr/cxZdlb.do", json, lp_ZiDianLeiBieList, funName);
}

//查询部门
function lp_cxBuMen(funName){
	var json = {"jsonObj":"{}"};
	lp_getData("/whwr/cxBm.do", json, lp_BuMenList, funName);
}