
/**
* 返回中英文字符串的实际长度，中文算两个字符
String.prototype.len = function(){
	return this.replace(/[^\x00-\xff]/g,"**").length;
}

* 返回中英文字符串的实际长度，中文算两个字符/大写字母算两个字符
String.prototype.realLength = function(){
	return this.replace(/[^\x00-\xff]/g,"**").replace(/[A-Z]/g,"**").length;
}
**/

/**
* jquery对象扩展方法
* 此方法是为了实现 数据表格 和 表单表格 的样式及其他操作
**/
jQuery.fn.extend({
	 builderDataTable : function(setting){                            //生成数据表格
		var def = {   //默认设置
            haveHead  : true,                                         //是否点有表头
            clickChangeColor : true,                                  //是否点击选中
            rightClickTriggerClick : false,                           //是否双击出发单击事件
            ifOnlyOneLine : true                                      //是否数据必须显示在同一行
        };   
        var set = $.extend(def,setting);                              //覆盖默认值
           
        return $(this).each(function(){  
           if($(this).attr("tagName").toUpperCase() != "TABLE"){      //非table不可以使用该方法
           	  alert("该对象不是表格！不能使用该方法！");
           	  return;
           }

           $(this).addClass("builderDataTable");                      //table Class
           $(this).find("tr:odd").removeClass("builderDataTableTrEven").addClass("builderDataTableTrOdd");  //table 奇数行
           $(this).find("tr:even").removeClass("builderDataTableTrOdd").addClass("builderDataTableTrEven");//table 偶数行
           if(set.haveHead){                                          //table 如果有表头
           	  $(this).find("tr:first").removeClass("builderDataTableTrEven")
           	                          .addClass("builderDataTableTrFirst");
           } 
           $(this).find(".builderDataTableTrOdd td").addClass("builderDataTableTd");//table td
           $(this).find(".builderDataTableTrEven td").addClass("builderDataTableTd");//table td
           var changeColor = function(){                              //改变选中行的颜色
           		if(set.haveHead){
           				if($(this).index() != 0){                     //如果有表头首行不进行点击变色
		           			table.find("tr").removeClass("builderDataTableTrClick");
		           			$(this).addClass("builderDataTableTrClick");
		           		}
           			}else{
          				table.find("tr").removeClass("builderDataTableTrClick");
	           			$(this).addClass("builderDataTableTrClick");
           		}
           }
           var table = $(this);
           if(set.clickChangeColor){                                  //table 如果单击
           		$(this).find("tr").bind({
           			click : changeColor
           		}); 
           }
           if(set.onClickRow){                                        //table 注册点击行事件
           		if(typeof set.onClickRow == "function"){
           			$(this).find("tr").bind("click",set.onClickRow);
           			if(set.haveHead){
           				$(this).find("tr:first").unbind("click");
           			}
           		}
           }
           if(set.rightClickTriggerClick){                                   //如果右击出发左击事件时
           		$(this).find("tr").bind("contextmenu",set.onClickRow);
           		$(this).find("tr").bind({
           			contextmenu : changeColor
           		}); 
		   }
		   if(set.onRightClickRow){                                   //table 注册右击行事件
	       		if(typeof set.onRightClickRow == "function"){
	       			$(this).find("tr").bind("contextmenu",set.onRightClickRow);
	       		}
           }
           
           
           if(set.haveHead){                                         //表头取消右键事件注册
           		$(this).find("tr:first").unbind("contextmenu");
           }
           if(set.width){                                             //table 总宽度设置
           		$(this).css("width",set.width);
           }
           if(set.tdWidth){                                           //table 每列宽度设置
           		var tableWidth = 0;		
           		var flagSetWidth = true;                              //是否要设置表的宽度                                  
           		$(this).find("tr:first").find("td,th").each(function(i){
           			$(this).css("width",set.tdWidth[i]);
           			if(set.tdWidth[i]){
           				tableWidth += set.tdWidth[i];
           			}else{
           				flagSetWidth = false;
           			}
           		});
           		if(flagSetWidth && !(set.width)){
           			table.css("width",tableWidth);
           		}else{
           			table.css("width","100%");
           		}
           }
           
           if(set.ifOnlyOneLine){//如果必须在同一行显示，则调整顺序
           		$(this).find('tr').each(function(i){
	           	   if(i <= 50 && $(this).attr('clientHeight') > 29){//先看哪一行有换行现象 50行以上将会影响性能,尽量使用分页处理
	           	   		if(i==0 && set.haveHead){
	           	   			//表头不做处理
	           	   		}else{
		           	   		/*$(this).find('td').each(function(j){
		           	   			  if($(this).html().realLength()> 500){//文字过长
		           	   				 $(this).attr('title',$(this).html());
		           	   				 $(this).html($(this).html().substring(0,20) + '...');
		           	   			  }else if($(this).attr('clientWidth') > 500){//列很宽或被撑到很宽
		           	   			  	 $(this).attr('title',$(this).html());
		           	   			  	 if($(this).html().realLength()>20){
		           	   			  	 	$(this).html($(this).html().substring(0,20) + '...');
		           	   			  	 }
		           	   			  }else if($(this).attr('clientWidth') < $(this).html().realLength()*6.2){
		           	   			  	 $(this).attr('title',$(this).html());
		           	   				 $(this).html($(this).html().substring(0,($(this).attr('clientHeight')/6)) + '...');
		           	   			  }
		           	   		});*/
	           	   		}
	           	   }
	           });
           }
        });   
	 },
	 builderFormTable : function(setting){                            //生成表单表格
		var def = {   //默认设置
            haveHead  : false                                         //是否点有表头
        };   
        var set = $.extend(def,setting);                              //覆盖默认值
           
        return $(this).each(function(){
        	if($(this).attr("tagName").toUpperCase() != "TABLE"){     //非table不可以使用该方法
           	  alert("该对象不是表格！不能使用该方法！");
           	  return;
            }
            $(this).addClass("builderFormTable");                     //table Class
            $(this).find("tr:even").addClass("builderFormTableTrEven");//table 偶数行
            $(this).find("tr").each(function(){
            	if($(this).index() == 0){
            		$(this).find("td,th").css("border-top-width","1px");//表格首行补上虚线
            	}
            	$(this).find("td:first").addClass("builderFormTableTrFirstTd");
            	if(!(set.tdWidth)){
            	  $(this).find("td:first").css("width","200px");      //默认表格首列为200px宽度
            	}
            });
            if(set.haveHead){
            	$(this).find("tr:first").removeClass("builderFormTableTrEven")
            	                        .addClass("builderFormTableFirstTr")
            	                        .find("td:not(:first),th:not(:first)").css("border-left-width","1px");
            }
            if(set.width){
           		$(this).css("width",set.width);
            }
            if(set.tdWidth){                                           //table 每列宽度设置
           		var tableWidth = 0;		
           		var flagSetWidth = true;                               //是否要设置表的宽度                                  
           		$(this).find("tr:first").find("td").each(function(i){
           			$(this).css("width",set.tdWidth[i]);
           			if(set.tdWidth[i]){
           				tableWidth += set.tdWidth[i];
           			}else{
           				flagSetWidth = false;
           			}
           		});
           		if(flagSetWidth && !(set.width)){
           			$(this).css("width",tableWidth);
           		}
           }
           $(this).find("input[type=text],input[type=password]").each(function(){
           		if($(this).attr("class") == ""){                      //为表单内部的input text 和 password添加样式
           			$(this).addClass("input");
           		}
           });
           $(this).find("input[type=reset],input[type=button],input[type=submit]").each(function(){
           		if($(this).attr("class") == ""){                      //为表单内部的input reset,button 和 submit添加样式
           			$(this).addClass("btn");
           		}
           });
           $(this).find("table").each(function(){//表格中的表格要删除任何样式
           		$(this).find("td,th").css("border-top-width","0px").css("border-bottom-width","0px");
                $(this).find("tr").removeClass("builderFormTableTrEven")
                                  .removeClass("builderFormTableFirstTr");
           });
        });
	 }
});
 