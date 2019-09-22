/**
 * ajax来读取文件
 */
function ajax(url,fnSucc)
{
    if(window.XMLHttpRequest)
        {
            var oAjax = new XMLHttpRequest();
        }
        else
        {
            var oAjax = new ActiveXObject("Microsoft.XMLHTTP");//IE6
        }
        oAjax.open("GET",url,true);//打开url服务器文件
        //alert("1");
        oAjax.send();
        oAjax.onreadystatechange=function()
        {
        	
            if(oAjax.readyState==4)
            {
            	
                if(oAjax.status==200)
                {
                	
                    fnSucc(oAjax.responseText);//返回文件内容
                }
                else
                {
                    if(fnfiled)
                    {
                        fnField(oAjax.status);
                    }
                }
            }
        };
}