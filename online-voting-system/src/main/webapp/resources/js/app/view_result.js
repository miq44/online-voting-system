$(document).ready(function(){
	
	var data = $('#data').val();
	data = JSON.parse(data);
	var html = '';
	
	for ( var postName in data){
		html+= getHeader(postName);
		
		var results= data[postName]; 
		var totalVoter = 0;
		for( var position in results ){
			var thisRow = results[position];
			totalVoter+= parseInt(thisRow.vote);
		}
		
		
		for( var position in results ){
			
			var row = results[position];
			html+= getRow(position,row,totalVoter);
			html+="<hr>";
		}
		html+= "<div id='"+postName.trim()+"'></div>";
		html+=getFooter();
		
	}
	$('#content_result').html(html);
	
	for ( var postName in data){
		var results= data[postName]; 
		var totalVoter = 0;
		for( var position in results ){
			var thisRow = results[position];
			totalVoter+= parseInt(thisRow.vote);
		}
		var x = ['x'];
        var value = ['value'];
        var percentage = ['Percentage'];
		
		for( var position in results ){
			
			var row = results[position];
			x.push(row.name);
			value.push(row.vote);
			percentage.push(((row.vote/totalVoter)*100).toFixed(2));
		}
		
		var columns = [x, percentage];
		generateBarChart(postName,columns);
	}
	
	function getHeader(postName){
		var html = "<div class='panel panel-success' id='candidate_general'>\r\n" + 
		"		<div class='panel-heading'>"+ postName +"</div>\r\n" + 
		"		<div class='panel-body'>";
		return html;
	}
	
	function getRow(position,obj,totalVoter){
		var siteUrl = $("#site_ulr").val();
		var positionHtml = "<center><img class='candidate-photo'" + 
		"				src='"+siteUrl+"/images/winner.png'></center>" ;
		if(position>0){
			positionHtml = "<center><h3>"+(parseInt(position)+1)+"</h3></center>";
		}
		var percentage = ((obj.vote/totalVoter)*100).toFixed(2);
		var html = "<div class='row'>" + 
		"		<div class='col-md-3'>" + 
		"		<div class='vertical-center'>" + 
		positionHtml+
		"		</div>" + 
		"	</div>" + 
		"	<div class='col-md-6'>" + 
		"		<div class='vertical-center' style='top: 8%;'>" + 
		"			<div class='form-group'>" + 
		"					<label for='election-name' class='col-sm-5 control-label'>Name</label>" + 
		"				" + 
		"				<div class='col-sm-7'>" + 
		"					<label>"+obj.name+"</label>" + 
		"				</div>" + 
		"			</div>" + 
		"			<div class='form-group'>" + 
		"					<label for='election-name' class='col-sm-5 control-label'>Email Id</label>" + 
		"				" + 
		"				<div class='col-sm-7'>" + 
		"					<label>"+obj.email+"</label>" + 
		"				</div>" + 
		"			</div>" + 
		"			<div class='form-group'>" + 
		"				<label for='election-name' class='col-sm-5 control-label'>Number" + 
		"					of Votes</label>" + 
		"				<div class='col-sm-7'>" + 
		"					<label>"+obj.vote+"</label>" + 
		"				</div>" + 
		"			</div>" + 
		"			<div class='form-group'>" + 
		"				<label for='election-name' class='col-sm-5 control-label'>Percentage</label>" + 
		"				<div class='col-sm-7'>" + 
		"					<label>"+percentage+" % </label>" + 
		"				</div>" + 
		"			</div>" + 
		"		</div>" + 
		"	</div>" + 
		"	<div class='col-md-3'>" + 
		"		<div class='vertical-center right'>" + 
		"			<img class='candidate-photo'" + 
		"				src='"+siteUrl+"/images/"+obj.photo+"'>" + 
		"		</div>" + 
		"" + 
		"	</div>" + 
		"</div>";
		return html;
	}
	
	function getFooter(){
		var html = "</div></div>";
		return html;
	}
	
	
	
	
	
	function generateBarChart(div, columns) {
		 var chart = c3.generate({
                bindto: '#' + div,
                padding: {
                    left: 120
                },
                data: {
                    x: 'x',
                    columns: columns,
                    type: 'bar',
                    color: function (color, d) {
                        
                            return '#7FDBFF';

                    }
                },
               axis: {
                    rotated: true,
                    x: {
                        type: 'category'
                    }
                },
                tooltip: {
                    format: {
//                    title: function (d) {
//                        return 'Data ' + d;
//                    },
                        value: function (value, ratio, id) {
                            return value + " %";
//                        var format = id === 'data1' ? d3.format(',') : d3.format('$');
//                        return format(value);
                        }
                    }
                }
            });

    }

	
	
	
	
	
});