<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<%@ page import="com.call.jsp.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>GITA E-Library</title>
    <!-- Meta Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- //Meta Tags -->

    <!-- Style-sheets -->
    <!-- Bootstrap Css -->
    <link href="css1/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!-- Bootstrap Css -->
    <!-- Bars Css -->
    <link rel="stylesheet" href="css1/bar.css">
    <!--// Bars Css -->
    <!-- Calender Css -->
    <link rel="stylesheet" type="text/css" href="css1/pignose.calender.css" />
    <!--// Calender Css -->
    <!-- Common Css -->
    <link href="css1/style.css" rel="stylesheet" type="text/css" media="all" />
    <!--// Common Css -->
    <!-- Nav Css -->
    <link rel="stylesheet" href="css1/style4.css">
    <!--// Nav Css -->
    <!-- Fontawesome Css -->
    <link href="css1/fontawesome-all.css" rel="stylesheet">
    <!--// Fontawesome Css -->
    <!--// Style-sheets -->

    <!--web-fonts-->
    <link href="//fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
    <!--//web-fonts-->
</head>

<body>
    <div class="se-pre-con"></div>
    <div class="wrapper">
        <!-- Sidebar Holder -->
        <nav id="sidebar">
            <div class="sidebar-header">
                <h1>
                    <a href="studenthome.jsp">GITA E-Library</a>
                </h1>
                <span>G</span>
            </div>
            <div class="profile-bg"></div>
                     <ul class="list-unstyled components">
                <li class="active">
                    <a href="studenthome.jsp">
                        <i class="fas fa-home"></i>
                        Home
                    </a>
                </li>
   
                <li>
                    <a href="studentseebook.jsp">
                        <i class="fas fa-book"></i>
                        Issue Book
                    </a>
                </li>
                
                <li>
                    <a href="studentprofile.jsp">
                        <i class="far fa-user"></i>
                        Profile
                    </a>
                </li>
                <li>
                    <a href="studentrecordbook.jsp">
                        <i class="fas fa-file"></i>
                        Record
                    </a>
                </li>
                
                <li>
                    <a href="studentreturnbook.jsp">
                        <i class="fas fa-upload"></i>
                        Return Book
                    </a>
                </li>
                
            </ul>
        </nav>

        <!-- Page Content Holder -->
        <div id="content">
            <!-- top-bar -->
            <nav class="navbar navbar-default mb-xl-5 mb-4">
                <div class="container-fluid">

                    <div class="navbar-header">
                        <button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
                            <i class="fas fa-bars"></i>
                        </button>
                    </div>
                    
                    <ul class="top-icons-agileits-w3layouts float-right">
                                                
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                                <i class="far fa-user"></i>
                            </a>
                            <div class="dropdown-menu drop-3">
                                <a class="dropdown-item" href="logout.jsp">Logout</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
            <!--// top-bar -->
            <div class="container-fluid">
                <div class="row">
                    
                   
            <!--// three-grids -->
            <div class="container-fluid">
                <div class="row">
                    <!-- Calender -->
                    <div class="outer-w3-agile col-xl mt-3" id="profileview">
<!--                  jsp code -->

<%
	String name=(String)session.getAttribute("na");
	session.setAttribute("na", name);
	try
	{
		/* Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","bithalb2"); */
		Connection cn = DBconnect.getConn();

		PreparedStatement ps=cn.prepareStatement("select * from student where name=(?)");
		ps.setString(1,name);

		ResultSet res=ps.executeQuery();
		if(res.next())
		{
			out.println("<center><h1>STUDENT PROFILE</h1><hr><hr>");
			out.println("<table border='1' width='50%'> <tr> <td bgcolor='#b94646' style='text-align:right'><b> Name : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(5)+" "+res.getString(6)+"</td></tr>");	
			out.println("<tr> <td bgcolor='#ffe6e6' style='text-align:right'><b> DOB : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(7)+"</td></tr>");
			out.println("<tr> <td bgcolor='#b94646' style='text-align:right'><b> Gender : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(8)+"</td></tr>");
			out.println("<tr> <td bgcolor='#ffe6e6' style='text-align:right'><b> Roll : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(4)+"</td></tr>");
			out.println("<tr> <td bgcolor='#b94646' style='text-align:right''><b> Department : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(9)+"</td></tr>");
			out.println("<tr> <td bgcolor='#ffe6e6' style='text-align:right'><b> Semester : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(10)+"</td></tr>");
			out.println("<tr> <td bgcolor='#b94646' style='text-align:right'><b> Email ID : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(3)+"</td></tr>");
			out.println("<tr> <td bgcolor='#ffe6e6' style='text-align:right'><b> Phone No. : </b></td> <td bgcolor='#ffe6e6' style='text-align:center'> "+res.getString(11)+"</td></tr></table></center>");
		}
		else
		{
			out.println("not connect");
		}
	}catch(Exception ee)
			{
		ee.printStackTrace();
			}
%>
                    </div>
                    <!--// Calender -->
                    <!-- Profile -->
                    
                    <!--// Profile -->
                    <!-- Browser stats -->
                    
                    <!--// Browser stats -->
                </div>
            </div>
            <!--// Three-grids -->
            
            <!-- Copyright --><center>
            <div class="copyright-w3layouts py-xl-3 py-2 mt-xl-5 mt-4 text-center">
                <p>© 2019 GITA E-Library . All Rights Reserved 
                </p>
            </div></center>
            <!--// Copyright -->
        </div>
    </div>


    <!-- Required common Js -->
    <script src='js/jquery-2.2.3.min.js'></script>
    <!-- //Required common Js -->
    
    <!-- loading-gif Js -->
    <script src="js/modernizr.js"></script>
    <script>
        //paste this code under head tag or in a seperate js file.
        // Wait for window load
        $(window).load(function () {
            // Animate loader off screen
            $(".se-pre-con").fadeOut("slow");;
        });
    </script>
    <!--// loading-gif Js -->

    <!-- Sidebar-nav Js -->
    <script>
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
            });
        });
    </script>
    <!--// Sidebar-nav Js -->

    <!-- Graph -->
    <script src="js/SimpleChart.js"></script>
    <script>
        var graphdata4 = {
            linecolor: "Random",
            title: "Thursday",
            values: [{
                    X: "6",
                    Y: 300.00
                },
                {
                    X: "7",
                    Y: 101.98
                },
                {
                    X: "8",
                    Y: 140.00
                },
                {
                    X: "9",
                    Y: 340.00
                },
                {
                    X: "10",
                    Y: 470.25
                },
                {
                    X: "11",
                    Y: 180.56
                },
                {
                    X: "12",
                    Y: 680.57
                },
                {
                    X: "13",
                    Y: 740.00
                },
                {
                    X: "14",
                    Y: 800.89
                },
                {
                    X: "15",
                    Y: 420.57
                },
                {
                    X: "16",
                    Y: 980.24
                },
                {
                    X: "17",
                    Y: 1080.00
                },
                {
                    X: "18",
                    Y: 140.24
                },
                {
                    X: "19",
                    Y: 140.58
                },
                {
                    X: "20",
                    Y: 110.54
                },
                {
                    X: "21",
                    Y: 480.00
                },
                {
                    X: "22",
                    Y: 580.00
                },
                {
                    X: "23",
                    Y: 340.89
                },
                {
                    X: "0",
                    Y: 100.26
                },
                {
                    X: "1",
                    Y: 1480.89
                },
                {
                    X: "2",
                    Y: 1380.87
                },
                {
                    X: "3",
                    Y: 1640.00
                },
                {
                    X: "4",
                    Y: 1700.00
                }
            ]
        };
        $(function () {
            $("#Hybridgraph").SimpleChart({
                ChartType: "Hybrid",
                toolwidth: "50",
                toolheight: "25",
                axiscolor: "#E6E6E6",
                textcolor: "#6E6E6E",
                showlegends: false,
                data: [graphdata4],
                legendsize: "140",
                legendposition: 'bottom',
                xaxislabel: 'Hours',
                title: 'Weekly Profit',
                yaxislabel: 'Profit in $'
            });
        });
    </script>
    <!--// Graph -->
    <!-- Bar-chart -->
    <script src="js/rumcaJS.js"></script>
    <script src="js/example.js"></script>
    <!--// Bar-chart -->
    <!-- Calender -->
    <script src="js/moment.min.js"></script>
    <script src="js/pignose.calender.js"></script>
    <script>
        //<![CDATA[
        $(function () {
            $('.calender').pignoseCalender({
                select: function (date, obj) {
                    obj.calender.parent().next().show().text('You selected ' +
                        (date[0] === null ? 'null' : date[0].format('YYYY-MM-DD')) +
                        '.');
                }
            });

            $('.multi-select-calender').pignoseCalender({
                multiple: true,
                select: function (date, obj) {
                    obj.calender.parent().next().show().text('You selected ' +
                        (date[0] === null ? 'null' : date[0].format('YYYY-MM-DD')) +
                        '~' +
                        (date[1] === null ? 'null' : date[1].format('YYYY-MM-DD')) +
                        '.');
                }
            });
        });
        //]]>
    </script>
    <!--// Calender -->

    <!-- profile-widget-dropdown js-->
    <script src="js/script.js"></script>
    <!--// profile-widget-dropdown js-->

    <!-- Count-down -->
    <script src="js/simplyCountdown.js"></script>
    <link href="css1/simplyCountdown.css" rel='stylesheet' type='text/css' />
    <script>
        var d = new Date();
        simplyCountdown('simply-countdown-custom', {
            year: d.getFullYear(),
            month: d.getMonth() + 2,
            day: 25
        });
    </script>
    <!--// Count-down -->

    <!-- pie-chart -->
    <script src='js/amcharts.js'></script>
    <script>
        var chart;
        var legend;

        var chartData = [{
                country: "Lithuania",
                value: 260
            },
            {
                country: "Ireland",
                value: 201
            },
            {
                country: "Germany",
                value: 65
            },
            {
                country: "Australia",
                value: 39
            },
            {
                country: "UK",
                value: 19
            },
            {
                country: "Latvia",
                value: 10
            }
        ];

        AmCharts.ready(function () {
            // PIE CHART
            chart = new AmCharts.AmPieChart();
            chart.dataProvider = chartData;
            chart.titleField = "country";
            chart.valueField = "value";
            chart.outlineColor = "";
            chart.outlineAlpha = 0.8;
            chart.outlineThickness = 2;
            // this makes the chart 3D
            chart.depth3D = 20;
            chart.angle = 30;

            // WRITE
            chart.write("chartdiv");
        });
    </script>
    <!--// pie-chart -->

    <!-- dropdown nav -->
    <script>
        $(document).ready(function () {
            $(".dropdown").hover(
                function () {
                    $('.dropdown-menu', this).stop(true, true).slideDown("fast");
                    $(this).toggleClass('open');
                },
                function () {
                    $('.dropdown-menu', this).stop(true, true).slideUp("fast");
                    $(this).toggleClass('open');
                }
            );
        });
    </script>
    <!-- //dropdown nav -->

    <!-- Js for bootstrap working-->
    <script src="js/bootstrap.min.js"></script>
    <!-- //Js for bootstrap working -->

</body>

</html>