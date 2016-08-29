<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

   <title>Login</title>
   <style>
   @import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
        .center
        {
            display:table; margin-left:auto; margin-right:auto;
        }
        .inputStyle
        { width:300px; }
        .frame {
            width: 500px;
            height: 300px;
            border: 1px solid #f6f6f6;
            box-shadow: 5px 5px 15px gray;
            border-radius: 10px;
            padding: 20px;
            font-weight: bold;
        }
        input[type="submit"] {
            background: #5fc660;
            border-radius: 5px;
            border: none;
            color: white;
            margin-top: 80px;
            padding: 10px 40px;
            float: right;
            cursor: pointer;
        }

        input[type="text"], input[type="password"] {
            margin-left: 20px;
            padding: 10px;
            font-size: 10px;
            border-radius: 5px;
            border: #dddddd solid 1px;
            width: 450px;
        }

   </style>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
 <br><br><br><br><br>
<div class= "center">
      <div class= "center">
        <div class="frame">
            <form onsubmit="return fn_Login();">
                <p>ID</p>
                <div><input type="text" name="txtUserID" id="txtUserID" placeholder="ID" required class="inputStyle"></div>
                <p>Password</p>
                <div><input type="password" name="txtPassword" id="txtPassword" placeholder="Password" required class="inputStyle"></div>
                <div class= center><input type="submit" name="Login" value="Login"></div>
            </form>
        </div>
      </div>
</div>

<script type="text/javascript">

fn_Login = function(){

   var isPass = false;

   var sPID = '';
   $.ajax({
         async: false,
         url: "./loginAjax",
         type: "post",
         dataType: "json",
         data:{
            UserID: $("#txtUserID").val(),
            Password: $("#txtPassword").val()
            },
         success:function(result)
         {
            sPID = result[0].sPID;

            if(sPID == ""){
               alert("로그인 정보가 일치하지 않습니다.");
            }
            else{
               isPass = true;
            }
         },
         error:function(result)
         {
            alert("로그인시 에러가 발생했습니다. 잠시 후 다시 시도해 주세요.");
         }
      });


   if(!isPass){
      return false;
   }

   //alert(sPID);

   document.location.href = "./pms";

   return false;
}

</script>

</body>


</html>