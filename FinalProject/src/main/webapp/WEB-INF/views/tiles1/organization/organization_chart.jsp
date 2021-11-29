<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/sankey.js"></script>
<script src="https://code.highcharts.com/modules/organization.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<style type="text/css">

	.highcharts-figure,
	.highcharts-data-table table {
	    min-width: 360px;
	    max-width: 800px;
	    margin: 1em auto;
	}
	
	.highcharts-data-table table {
	    font-family: Verdana, sans-serif;
	    border-collapse: collapse;
	    border: 1px solid #ebebeb;
	    margin: 10px auto;
	    text-align: center;
	    width: 100%;
	    max-width: 500px;
	}
	
	.highcharts-data-table caption {
	    padding: 1em 0;
	    font-size: 1.2em;
	    color: #555;
	}
	
	.highcharts-data-table th {
	    font-weight: 600;
	    padding: 0.5em;
	}
	
	.highcharts-data-table td,
	.highcharts-data-table th,
	.highcharts-data-table caption {
	    padding: 0.5em;
	}
	
	.highcharts-data-table thead tr,
	.highcharts-data-table tr:nth-child(even) {
	    background: #f8f8f8;
	}
	
	.highcharts-data-table tr:hover {
	    background: #f1f7ff;
	}
	
	#container h4 {
	    text-transform: none;
	    font-size: 14px;
	    font-weight: normal;
	}
	
	#container p {
	    font-size: 13px;
	    line-height: 16px;
	}
	
	@media screen and (max-width: 600px) {
	    #container h4 {
	        font-size: 2.3vw;
	        line-height: 3vw;
	    }
	
	    #container p {
	        font-size: 2.3vw;
	        line-height: 3vw;
	    }
	}
	

</style>

<script type="text/javascript">
	
	$(document).ready(function() {
		func_organization();
	});// end of $(document).ready(function() {})

	
	// === Function Declaration === //
	function func_organization() {
		$.ajax({
			url:"<%= request.getContextPath()%>/getOrganizationList.gw",
			type:"GET",
			dataType:"JSON",
			success:function(json) {
				if(json != null) {
					
				}
			}, 
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
        	}
		});
		
		Highcharts.chart('container', {
		    chart: {
		        height: 600,
		        inverted: true
		    },

		    title: {
		        text: '회사 조직도 차트'
		    },

		    accessibility: {
		        point: {
		            descriptionFormatter: function (point) {
		                var nodeName = point.toNode.name,
		                    nodeId = point.toNode.id,
		                    nodeDesc = nodeName === nodeId ? nodeName : nodeName + ', ' + nodeId,
		                    parentDesc = point.fromNode.id;
		                return point.index + '. ' + nodeDesc + ', reports to ' + parentDesc + '.';
		            }
		        }
		    },

		    series: [{
		        type: 'organization',
		        name: 'Highsoft',
		        keys: ['from', 'to'],
		        data: [
		            ['Shareholders', 'Board'],
		            ['Board', 'CEO'],
		            ['CEO', 'CTO'],
		            ['CEO', 'CPO'],
		            ['CEO', 'CSO'],
		            ['CEO', 'HR'],
		            ['CTO', 'Product'],
		            ['CTO', 'Web'],
		            ['CSO', 'Sales'],
		            ['HR', 'Market'],
		            ['CSO', 'Market'],
		            ['HR', 'Market'],
		            ['CTO', 'Market']
		        ],
		        levels: [{
		            level: 0,
		            color: 'silver',
		            dataLabels: {
		                color: 'black'
		            },
		            height: 25
		        }, {
		            level: 1,
		            color: 'silver',
		            dataLabels: {
		                color: 'black'
		            },
		            height: 25
		        }, {
		            level: 2,
		            color: '#980104'
		        }, {
		            level: 4,
		            color: '#359154'
		        }],
		        nodes: [{
		            id: 'Shareholders'
		        }, {
		            id: 'Board'
		        }, {
		            id: 'CEO',
		            title: 'CEO',
		            name: 'Grethe Hjetland',
		            image: 'https://wp-assets.highcharts.com/www-highcharts-com/blog/wp-content/uploads/2020/03/17131126/Highsoft_03862_.jpg'
		        }, {
		            id: 'HR',
		            title: 'HR/CFO',
		            name: 'Anne Jorunn Fjærestad',
		            color: '#007ad0',
		            image: 'https://wp-assets.highcharts.com/www-highcharts-com/blog/wp-content/uploads/2020/03/17131210/Highsoft_04045_.jpg'
		        }, {
		            id: 'CTO',
		            title: 'CTO',
		            name: 'Christer Vasseng',
		            image: 'https://wp-assets.highcharts.com/www-highcharts-com/blog/wp-content/uploads/2020/03/17131120/Highsoft_04074_.jpg'
		        }, {
		            id: 'CPO',
		            title: 'CPO',
		            name: 'Torstein Hønsi',
		            image: 'https://wp-assets.highcharts.com/www-highcharts-com/blog/wp-content/uploads/2020/03/17131213/Highsoft_03998_.jpg'
		        }, {
		            id: 'CSO',
		            title: 'CSO',
		            name: 'Anita Nesse',
		            image: 'https://wp-assets.highcharts.com/www-highcharts-com/blog/wp-content/uploads/2020/03/17131156/Highsoft_03834_.jpg'
		        }, {
		            id: 'Product',
		            name: 'Product developers'
		        }, {
		            id: 'Web',
		            name: 'Web devs, sys admin'
		        }, {
		            id: 'Sales',
		            name: 'Sales team'
		        }, {
		            id: 'Market',
		            name: 'Marketing team'	
		        }],
		        colorByPoint: false,
		        color: '#007ad0',
		        dataLabels: {
		            color: 'white'
		        },
		        borderColor: 'white',
		        nodeWidth: 65
		    }],
		    tooltip: {
		        outside: true
		    },
		    exporting: {
		        allowHTML: true,
		        sourceWidth: 800,
		        sourceHeight: 600
		    }

		});
	}// end of function func_organization() { }
	
	
</script>

<figure class="highcharts-figure mt-4">
    <div id="container" class="mt-4"></div>
</figure>
