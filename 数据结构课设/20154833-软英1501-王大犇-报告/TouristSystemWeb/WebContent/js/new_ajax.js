/**
 * ajax����ȡ�ļ�
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
        oAjax.open("GET",url,true);//��url�������ļ�
        //alert("1");
        oAjax.send();
        oAjax.onreadystatechange=function()
        {
        	
            if(oAjax.readyState==4)
            {
            	
                if(oAjax.status==200)
                {
                	
                    fnSucc(oAjax.responseText);//�����ļ�����
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