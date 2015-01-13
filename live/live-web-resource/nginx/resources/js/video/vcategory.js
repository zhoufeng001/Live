$(function(){

	zfpager.init({
		pno : pageno ,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecored,
		
		getLink : function(n){
			return ctx + "/video/category/" + category + "/" + n + "/" + orderby + ".htm" ;
		} 
	});
	zfpager.generPageHtml();
	
});