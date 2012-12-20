(function(_){
    var _editor = window.AnUBBEditor=window.jsu =function(obj,extend){
        this.obj= _.$(obj);
        if(this.obj.tagName.toLowerCase()!="textarea"){alert("非法绑定，只能绑定文本域(TEXTAREA)标签");return;}
        if(_f.editors[this.obj.id]){return}
        this.Version="AnUBBEditorV1.0";
        this.pre=false;
        this.buttons={}; 
        this.addStyle();
        this.box=this.creEle("div","id",this.obj.id + "_box");
        this.obj.parentNode.insertBefore(this.box,this.obj);
        this.menu1=this.creMenu(this.obj.id + "_menu1",_f.menus,_f.separator1);
        this.menu2=this.creMenu(this.obj.id + "_menu2",_f.extend,_f.separator2);
        this.box.appendChild(this.obj);
        this.box.insertBefore(this.menu1,this.obj); 
        if(extend){
            this.extend=true;
            this.box.insertBefore(this.menu2,this.obj);
        }
        _f.editors[this.obj.id]=true;
        document.onmousedown=document.body.onmousedown=function(e){e = e ? e : (window.event ? window.event : null);var target=e.target ? e.target:e.srcElement;var tag=target.tagName.toLowerCase();if(tag=="html" || tag=="body" || target==_.$(obj)){return _f.hide();};};
    };
    jsu.prototype.creEle=function(a,b,c){var d=document.createElement(a);if(b.toLowerCase()=="id"){d.id=c;}else{d.className=c;}return d;};
    jsu.prototype.creMenu=function(id,menuConfig,splitor){
        var _menu=this.creEle("div","id",id);
        _menu.className="ANPlus_menu";
        for(var name in menuConfig){
            this.buttons[name]=this.creImage(menuConfig[name],name);
            _menu.appendChild(this.buttons[name]);    
        }
        if(splitor){
            for(var _s in splitor){
                if(menuConfig[splitor[_s]]!= undefined){
                    this.addSplit(_menu,this.buttons[splitor[_s]]);
                }
            }
        }
        return _menu;
    };
    jsu.prototype.addButton=function(name,title,src,fn){
        var _this=this;
        var o=_this.obj;
        var _ele=document.createElement("img");
        _ele.id=this.obj.id + "_" + name;
        _ele.src=src;
        _ele.title=title;
        _ele.className="ANPlus_img_normal";
        _ele.onmouseover=function(){this.className="ANPlus_img_hover";};
        _ele.onmouseout=function(){this.className="ANPlus_img_normal";};
        _ele.onclick=fn;
        this.menu2.appendChild(_ele);
        this.buttons[name]=_ele;
        return _ele;
    };
    jsu.prototype.removeButton=function(s){
        if(!this.buttons[s]){return};
        var _menu=this.menu1;
        for(var _child in _menu.childNodes){
            if(_menu.childNodes[_child]==this.buttons[s]){
                _menu.removeChild(this.buttons[s]);return;
            }
        } 
        var _menu=this.menu2;
        for(var _child in _menu.childNodes){
            if(_menu.childNodes[_child]==this.buttons[s]){
                _menu.removeChild(this.buttons[s]);return;
            }
        }
    };
    jsu.prototype.addSplit=function(o,b){
            var _split=document.createElement("img");
            _split.src=_f.buttonSrc + "/bb_separator.gif";
            _split.className="ANPlus_separator";
            o.insertBefore(_split,b);
            return _split;
    };
    jsu.prototype.creImage=function(title,name){
        var _this=this;
        var o=_this.obj;
        var _ele=document.createElement("img");
        _ele.id=this.obj.id + "_" + name;  
        _ele.title=title.title;
        _ele.src=_f.buttonSrc + "/button.gif";
        _ele.className="ANPlus_img_normal";
        _ele.style.backgroundPosition="0px " + title.y + "px";
        _ele.onmouseover=function(){this.className="ANPlus_img_hover";};
        _ele.onmouseout=function(){this.className="ANPlus_img_normal";};
        _ele.onclick=function(e){
            if(_this.pre && name!="preview" && name!="info"){_.$("an_over").style.display="none";_this.pre=!_this.pre;return;}
            e = e ? e : (window.event ? window.event : null);
            var target=e.target ? e.target:e.srcElement;
            if (o) {o.focus();if (o && o.document && o.document.selection){_f.range = o.document.selection.createRange();}} else {_f.range = null;}
            var s={width:200,height:100,html:""};
            var HD=true;
            var _clo="<input class=\"but\" type=\"button\" value=\"关闭\" onclick=\"_f.hide();\" />";
            switch(name){
                case "bold":_f.IT(o,"[b]" + _f.GT(o) + "[/b]");HD=false;break;
                case "italic":_f.IT(o,"[i]" + _f.GT(o) + "[/i]");HD=false;break;
                case "underline":_f.IT(o,"[u]" + _f.GT(o) + "[/u]");HD=false;break;
                case "code":_f.IT(o,"[code]" + _f.GT(o) + "[/code]");HD=false;break;
                case "quote":_f.IT(o,"[quote]" + _f.GT(o) + "[/quote]");HD=false;break;
                case "left":_f.IT(o,"[align=left]" + _f.GT(o) + "[/align]");HD=false;break;
                case "center":_f.IT(o,"[align=center]" + _f.GT(o) + "[/align]");HD=false;break;
                case "right":_f.IT(o,"[align=right]" + _f.GT(o) + "[/align]");HD=false;break;
                case "removeformat":_f.IT(o.id,_f.GT(o.id).replace(/\[.+?\]/g,""));HD=false;break;
                case "unlink":var reStr=_f.GT(o.id).replace(/\[url=(.[^\[]*)\]((.|\n)*?)\[\/url\]/,"$2");reStr=reStr.replace(/(\[url\])(.[^\[]*)(\[\/url\])/,"$2");_f.IT(o.id,reStr);HD=false;break;
                case "sup":_f.IT(o,"[sup]" + _f.GT(o) + "[/sup]");HD=false;break;
                case "sub":_f.IT(o,"[sub]" + _f.GT(o) + "[/sub]");HD=false;break;
                case "unorderedlist":var lists=_f.GT(o).split("\n");var str="";for(var i in lists){str+="[*]" + lists[i];}_f.IT(o,"[ulist]" + str + "[/ulist]");HD=false;break;
                case "orderedlist":var lists=_f.GT(o).split("\n");var str="";for(var i in lists){str+="[*]" + lists[i];}_f.IT(o,"[list=1]" + str + "[/list]");HD=false;break;
                case "indent":_f.IT(o,"[indent]" + _f.GT(o) + "[/indent]");HD=false;break;
                case "preview":
                    if(_this.pre){
                        _.$("an_over").style.display="none";
                    }else{
                        var ps=_.abs(_this.obj);
                        var over=_f.showOver(_this,_this.box.offsetWidth,_this.obj.offsetHeight,ps.x,ps.y);
                        over.innerHTML=_f.ubb2html(_.trim(_f.getText(o)));
                        //over.contentEditable=true;
                        over.focus();
                    }
                    _this.pre=!_this.pre;
                    return; 
                    break;
                case "font":
                    s.width=117;
                    for(var i=0;i<_f.fonts.length;i++){
                        s.html+="<a href=\"javascript:void(0);\" onmouseover=\"this.style.backgroundColor='#D5D2CA';\" onmouseout=\"this.style.backgroundColor='#eeeeee';\" onclick=\"_f.insertO\(\'" + o.id + "\','font',\'" + _f.fonts[i] + "\'\)\;\" style=\"font-family:" + _f.fonts[i] + ";display:block;width:110px;height:20px;text-align:center;padding:3px 0 0 0;\">" + _f.fonts[i] + "</a>";
                    }
                    break;
                case "color":
                    s.width=223;
                    var co="",colors=_f.colors;
                    for(var i=0;i<colors.length;i++){
                        for(var j=0;j<colors.length;j++){
                            for(var k=0;k<colors.length;k++){
                                co="#" + colors[i] + colors[j] + colors[k];
                                s.html+="\<input type\=\"button\" title=\"" + co + "\" style\=\"padding:0;height:10px;width:10px;margin:1px;border:0px;background-color: " + co + "\" onclick\=\"_f.insertO\(\'" + o.id + "\','color',\'" + co + "\'\)\;\" \/\>";
                            }
                        }
                    }
                    s.html+="<br />";
                    break;
                case "size":
                    s.width=80;
                    for(var i=0;i<_f.sizes.length;i++){
                        s.html+="<a href=\"javascript:void(0);\" onclick=\"_f.insertO\(\'" + o.id + "\','size',\'" + _f.sizes[i] + "\'\)\;\" style=\"display:block;width:73px;text-align:center;padding:3px 0 0 0;\"><span style=\"font-size:" + _f.sizes[i] + ";\">" + _f.sizes[i] + "</font></a>";
                    }
                    break;
                case "url":
                    s.width=250;
                    var selText=_f.GT(o.id);
                    var src="http://",txt="";
                    var reg1=/^\[url=(.[^\[]*)\]((.|\n)*?)\[\/url\]$/i;
                    var reg2=/^(\[url\])(.[^\[]*)(\[\/url\])$/i;
                    if(reg1.test(selText)){
                        src=reg1.exec(selText)[1];
                        txt=reg1.exec(selText)[2];
                    }else if (reg2.test(selText)){
                        src=reg2.exec(selText)[2];
                    }else{
                        txt=selText;
                    } 
                    s.html+="URL: <input id=\"link_url\" type=\"text\" value=\"" + src + "\" size=\"32\" /><br />";
                    s.html+="文本: <input id=\"link_txt\" type=\"text\" value=\"" + txt + "\" size=\"32\" /><br />";
                    s.html+="<input class=\"but\" type=\"button\" value=\"确定\" onclick=\"_f.insertUrl\(\'" + o.id + "\', _.$\(\'link_txt\'\)\.value,_.$\(\'link_url\'\)\.value\)\;\" /> ";
                    s.html+=_clo;
                    break;
                case "table":
                    s.width=100;
                    s.html+="行数: <input id=\"table_rows\" type=\"text\" value=\"\" size=\"7\" /><br />";
                    s.html+="列数: <input id=\"table_cols\" type=\"text\" value=\"\" size=\"7\" /><br />";
                    s.html+="<input class=\"but\" type=\"button\" value=\"确定\" onclick=\"_f.insertTable\(\'" + o.id + "\',_.$\(\'table_rows\'\)\.value,_.$\(\'table_cols\'\)\.value\)\;\" /> ";
                    s.html+=_clo;
                    break;
               
                default:HD=false;
            }
            if(HD){var x=_f.Dialog(_.abs(target),s,title.title);}
        };
        return _ele;
    };
    jsu.prototype.addStyle=function(){
        var cT="";
        ediID=this.obj.id;
        cT += "#" + ediID + "{border:0px;font-size:9pt;margin:0px;padding:0px;width:" + (parseInt(this.obj.offsetWidth)) + "px}";
        cT += "#" + ediID + "_box{width:" + (parseInt(this.obj.offsetWidth)) + "px;height:auto;border:1px solid " + _f.style.box_border + ";padding:0;font-family:verdana,tahoma,arial;}";
        cT += ".ANPlus_menu{height:25px;width:100%;background:url(" + _f.buttonSrc + "/" + _f.style.bg_img + ") repeat-x;padding:0px;}";
        cT += ".ANPlus_img_normal {background-image:url(" + _f.buttonSrc  + "/editor.gif);cursor:pointer;width:21px;height:20px;vertical-align:top;border:0px solid #eeeeee;margin:2px 1px 1px 2px;}";
        cT += ".ANPlus_img_hover  {background-image:url(" + _f.buttonSrc  + "/editor.gif);cursor:pointer;width:21px;height:20px;vertical-align:top;border:1px " + _f.style.img_hover_border + " solid;margin:1px 0 0 1px;background-color:" + _f.style.img_hover_bg + ";}";
        cT += ".ANPlus_separator  {vertical-align:top;margin:6px auto auto 3px;width:2px;height:11px;}";
        cT += ".ANPlus_box {position:absolute;border:1px solid " + _f.style.dialog_border + ";background-color:" + _f.style.dialog_bg + ";height:auto;}";
        cT += ".ANPlus_box .content A:link {COLOR:" + _f.style.a_color + "; background:" + _f.style.dialog_bg + ";TEXT-DECORATION: none;}";
        cT += ".ANPlus_box .content A:hover {COLOR:" + _f.style.a_color + "; background:" + _f.style.box_border + ";TEXT-DECORATION: none;}";
        cT += ".ANPlus_box .content INPUT {border:1px " + _f.style.dialog_input_border + " solid;font-size:9pt;padding:3px 3px 0 3px;margin:2px 0 2px 0;}";
        cT += ".ANPlus_box .caption A{COLOR:" + _f.style.a_color + "; background:none;TEXT-DECORATION: none;margin:-2px 3px 0 0;}";
        cT += ".ANPlus_box .but {border:1px " + _f.style.dialog_border + " solid;background:url(" + _f.buttonSrc + "/" + _f.style.bg_img + ") repeat-x;}";
        cT += ".ANPlus_box .caption  {height:18px;padding:5px 0 0 5px;background:url(" + _f.buttonSrc + "/" + _f.style.bg_img + ") repeat-x;}";
        cT += ".ANPlus_box .content  {text-align:left;padding:3px;}";
        cT += "#an_over{filter:alpha(opacity=100);opacity:1 !important;background:#ffffff;position:absolute;overflow-y:scroll;border:0px;font-size:9pt;padding:3px;margin:0;}";
        cT += "#AnUBBEditor_Preview {display:none;padding:3px;overflow-y:scroll;width:" + (parseInt(this.obj.offsetWidth)-6) + "px;height:" + (parseInt(this.obj.offsetHeight)) + "px;}";
        var STYLE=document.createElement('style');STYLE.setAttribute("type","text/css");STYLE.styleSheet&&(STYLE.styleSheet.cssText=cT)||STYLE.appendChild(document.createTextNode(cT));document.getElementsByTagName('head')[0].appendChild(STYLE);};
    //编辑器配置程序_f
    _f={
        range:null,
        buttonSrc:"editor_img",
        editors:{},
        style:{
			a_color:"#000000",
			box_border:"#D5D2CA",
			img_hover_bg:"#B6BDD2",
			img_hover_border:"#0A246A",
			dialog_border:"#aaaaaa",
			dialog_bg:"#eeeeee",
			dialog_input_border:"#dddddd",
			bg_img:"menu_bg.gif"},
        menus:{
            font:{y:-400,title:"选择字体"},
            size:{y:-420,title:"字体大小"},
            color:{y:-560,title:"字体颜色"},
            bold:{y:0,title:"粗体"},
            italic:{y:-20,title:"斜体"},
            underline:{y:-40,title:"下划线"},
            left:{y:-60,title:"左对齐"},
            center:{y:-80,title:"居中对齐"},
            right:{y:-100,title:"右对齐"},
            url:{y:-120,title:"插入超级链接"},
            unlink:{y:-200,title:"取消超级链接"}
        },
        extend:{
            sup:{y:-480,title:"上标"},
            sub:{y:-500,title:"下标"},
            unorderedlist:{y:-280,title:"无序列表"},
            orderedlist:{y:-260,title:"有序列表"},
            table:{y:-380,title:"创建表格"},
            code:{y:-460,title:"代码段"},
            quote:{y:-440,title:"引用"},
            removeformat:{y:-180,title:"去除格式"},
            preview:{y:-620,title:"HTML预览"}
        },
        colors:["00","33","66","99","CC","FF"],
        fonts:["仿宋_GB2312", "黑体", "楷体_GB2312", "宋体", "新宋体", "Tahoma", "Arial", "Impact", "Verdana", "Times New Roman"],
        sizes:["8px","9px","10px","11px","12px","13px","14px","16px","18px","20px","24px","28px"],
        separator1:["font","bold"],
        separator2:["sup","table","removeformat","preview"],
        insertTable:function(o,r,c){
            r==isNaN(r) ? 0 : r;
            c==isNaN(c) ? 0 : c;
            if(r==0 || c==0){
                this.hide();
                return;
            }
            var text="[table]";
            for(var i=0;i<r;i++){
                text+="[tr]";
                for(var j=0;j<c;j++){
                    text+="[td][/td]";
                }
                text+="[/tr]";
            }
            this.IT(o,text + "[/table]");
        },
        insertO:function(o,typ,val){
            o=_.$(o);
            this.IT(o,"[" + typ + "=" + val + "]" + this.GT(o) + "[/" + typ + "]");
        },
        insertUrl:function(o,txt,link){
            var text="";
            if(_.trim(link).length<=0){
                this.hide();
                return;
            }else if(_.trim(txt).length<=0){
                text="[url]" + link + "[/url]";
            }else{
                text="[url=" + link + "]" + txt + "[/url]";
            }
            this.IT(o,text);
        },
        insertImg:function(o,src){
            if(_.trim(src).length<=0){
                this.hide();
                return;
            }
            this.IT(o,"[img]" + src + "[/img]");
        },
        hide:function(){
            if(_.$("an_dialog")){
                 _.$("an_dialog").style.display="none";
            }
            return this;
        },
        showOver:function(_this,w,h,l,t){
            if(_.$("an_over")){
                var over=_.$("an_over");
                over.style.display="block";
            }else{
                var over=_this.creEle("div","id","an_over");
                _this.box.appendChild(over);
            }
            var ps=_.abs(_this.box);
            over.style.top=parseInt(t)+ "px";
            over.style.left=(parseInt(l)+1) + "px";
            over.style.width=(parseInt(w)-8) +"px";
            over.style.height=(parseInt(h)-4) +"px";
            if(!_.is_ie){over.style.height=(parseInt(h)-6) +"px";}
            _.getFocus(over);
            return over;
        },
        Dialog:function(o,s,b){
            if(!_.$("an_dialog")){
                var box=document.createElement("div");
                var box_caption=document.createElement("div");
                var box_content=document.createElement("div");
                _.EndragEx(box_caption,box,0,0);
                box.id="an_dialog";
                box.className="ANPlus_box";
                box_caption.id="an_dialog_caption";
                box_caption.className="caption";
                box_content.id="an_dialog_content";
                box_content.className="content";
                box.appendChild(box_caption);
                box.appendChild(box_content);
                document.body.appendChild(box);
            }else{
                var box=_.$("an_dialog");
                var box_caption=_.$("an_dialog_caption");
                var box_content=_.$("an_dialog_content");
            }
            box_caption.innerHTML= "\<a style=\"float:right;\" href=\"javascript:void(0);\"\ title=\"关闭\" onclick=\"_f.hide();\">×\</a\>" + b;
            box_content.innerHTML=s.html;
            box.style.width=s.width + "px";
            box.style.left=(o.x + 5)+ "px";
            box.style.top=(o.y + 23) + "px";
            box.style.display="block";
            box.focus();
            _.getFocus(box);
            return box;
        },
        IT:function (a,b){
            b=b.replace("&quot;","\"");
            var a=_.$(a);
            if (!a) {return;}
            a.focus();
            if (this.range) {
                this.range.text = b;
                this.range.select();
                this.range = null;
            } else if (a.document && a.document.selection){
                a.document.selection.createRange().text = b;
            }else if (typeof a.selectionStart != "undefined") {
                var str = a.value;
                var start = a.selectionStart;
                var top = a.scrollTop;
                a.value = str.substr(0, start) + b + str.substring(a.selectionEnd, str.length);
                a.selectionStart = start + b.length;
                a.selectionEnd = start + b.length;
                a.scrollTop = top;
            }
            if(_.$("an_dialog")) {
                _.$("an_dialog").style.display="none";
            }
        },
        getText:function (a){
            var o=_.$(a);
            if (o){
                return o.value;
            }
        },
        setText:function (a,b){
            var o=_.$(a);
            if (o) {
                return o.value = b;
            }
        },
        GT:function (a){
            var o=_.$(a);
            var str="";
            if (!o){return;}
            o.focus();
            if (this.range){
                str=this.range.text;
            }else if (o.document && o.document.selection){
                str=o.document.selection.createRange().text;
            }else if (typeof o.selectionStart != "undefined"){
                str=o.value.substring(o.selectionStart, o.selectionEnd);
            }
            return str.replace("\"","&quot;");
        },
        ubb2html:function(o){
            var re=null;
            var str=o;
	        str = str.replace(/</ig, "&lt;");
	        str = str.replace(/>/ig, "&gt;");
	        str = str.replace(/ /ig, "&nbsp;");
	        str = str.replace(/\n/ig,"<br />\n")
	        str = str.replace(/\[img\](.[^\[]*)\[\/img\]/ig,"<a href=\"$1\" target=\"_blank\"><img src=\"$1\" border=\"0\" alt=\"Open in a new window!\" onload=\"javascript:if(this.width>500){this.width=500;}\" /></a>");
	        str = str.replace(/(\[url\])(.[^\[]*)(\[\/url\])/ig,"<a href=\"$2\" target=\"_blank\">$2</a>");
	        str = str.replace(/\[url=(.[^\[]*)\]/ig,"<a href=\"$1\" target=\"_blank\">");
	        str = str.replace(/\[color=(.[^\[]*)\]/ig,"<span style=\"color:$1;\">");
	        str = str.replace(/\[font=(.[^\[]*)\]/ig,"<span style=\"font-family:$1;\">");
	        str = str.replace(/\[size=(.[^\[]*)\]/ig,"<span style=\"font-size:$1;\">");
	        str = str.replace(/\[align=(center|left|right)\]/ig,"<div align=\"$1\">");
	        str = str.replace(/\[table=(.[^\[]*)\]/ig,"<table width=\"$1\">");
            ubbTag =new Array(/\[sub\]/ig,/\[\/sub\]/ig,/\[sup\]/ig,/\[\/sup\]/ig,/\[\/url\]/ig,/\[\/color\]/ig, /\[\/size\]/ig, /\[\/font\]/ig, /\[\/align\]/ig, /\[b\]/ig, /\[\/b\]/ig,/\[i\]/ig, /\[\/i\]/ig, /\[u\]/ig, /\[\/u\]/ig, /\[ulist\]/ig, /\[list=1\]/ig, /\[list=a\]/ig,/\[list=A\]/ig, /\[\*\]/ig, /\[\/ulist\]/ig,/\[\/list\]/ig, /\[indent\]/ig, /\[\/indent\]/ig,/\[code\]/ig,/\[\/code\]/ig,/\[quote\]/ig,/\[\/quote\]/ig,/\[table\]/ig,/\[tr\]/ig,/\[td\]/ig,/\[\/tr\]/ig,/\[\/td\]/ig,/\[\/table\]/ig);
	        htmlTag=new Array("<sub>","</sub>","<sup>","</sup>","</a>","</span>", "</span>", "</span>", "</div>", "<b>", "</b>", "<i>","</i>", "<u>", "</u>", "<ul>", "<ol type=\"1\">", "<ol type=\"a\">","<ol type=\"A\">", "<li>", "</ul>", "</ol>", "<blockquote>", "</blockquote>","<div style=\"background:#E2F2FF;width:80%;border:1px solid #3CAAEC;padding:3px;\">","</div>","<div style=\"background:#E2F2FF;width:80%;border:1px solid #3CAAEC;padding:3px;\">","</div>","<table style=\"width:80%;border:1px solid #3CAAEC;border-collapse:collapse;\">","<tr>","<td style=\"border:1px solid #3CAAEC;\">","</tr>","</td>","</table>");
	        for(var i=0;i<ubbTag.length;i++){str=str.replace(ubbTag[i],htmlTag[i]);}
	        return str;
        }
    };
})(_);    