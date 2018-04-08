$(document)
		.ready(
				function() {
					

					$("#election-crete")
							.on(
									"click",
									function() {
										$("#num_of_position_error").html("");
										$("#election_name_error").html("");
										var numOfPosition = $(
												"#num_of_position").val();
										var electionName = $("#election_name")
												.val();
										var errorStatus = false;
										if (numOfPosition === "") {
											$("#num_of_position_error").html(
													"Insert a valid number");
											errorStatus = true;
										}

										if (electionName === "") {
											$("#election_name_error").html(
													"Insert a valid name");
											errorStatus = true;
										}

										if (!errorStatus) {
											$("#election-general").hide();
											var baseUrl = $("#site_ulr").val();
											var data = {};
											data["numOfPosition"] = numOfPosition;
											data["electionName"] = electionName;
											data = JSON.stringify(data);

											$
													.ajax({
														type : "POST",
														url : baseUrl
																+ "/ajaxrequest/generateCandidateUI",
														data : data,
														contentType : "application/json; charset=utf-8",
														dataType : "text",
														success : function(data) {
															$("#election-info-div")
																	.html(data);
														},
														error : function(error) {
															console.log(error);

														}
													});

										}

									});

					

					$(".edit-voter").on(
							"click",
							function() {
								var emailId = $(this).closest("tr").find(
										".email_id").html();
								var voterId = $(this).closest("tr").find(
										".voter-id").val();
								$("#voter_email_id").val(emailId);
								$("#voter_id").val(voterId);
							});

					$(".delete-voter").on(
							"click",
							function() {
								var emailId = $(this).closest("tr").find(
										".email_id").html();
								var voterId = $(this).closest("tr").find(
										".voter-id").val();
								$("#email_id_to_delete").html(emailId);
								$("#voter_id_to_delete").val(voterId);
								$("#voter_email_id_to_delete").val(emailId);
							});

					$(".begin-voting").on(
							"click",
							function() {
								var electionId = $(this).closest("tr").find(
										".election-id").val();
								$("#election_id_to_begin_election").val(
										electionId);
							});

					$(".end-voting").on(
							"click",
							function() {
								var electionId = $(this).closest("tr").find(
										".election-id").val();
								$("#election_id_to_end_election").val(
										electionId);
								console.log(electionId);
							});

					$(".image_upload").change(function() {
						loadImage(this);
					});

					function loadImage(event) {

						if (event.files && event.files[0]) {

							var reader = new FileReader();
							reader.onload = function(e) {
								$(event).closest("div")
										.find(".candidate-photo").attr("src",
												e.target.result);
							}
							reader.readAsDataURL(event.files[0]);
							$("#candidates_detail_submit").prop('disabled',
									true);
							var myFormData = new FormData();
							myFormData.append("file", event.files[0]);

							var baseUrl = $("#site_ulr").val();
							// Ajax call for file uploaling
							var ajaxReq = $
									.ajax({
										url : baseUrl
												+ "/ajaxrequest/postFileUpload",
										type : 'POST',
										data : myFormData,
										cache : false,
										contentType : false,
										processData : false,
										xhr : function() {
											// Get XmlHttpRequest object
											var xhr = $.ajaxSettings.xhr();

											// Set onprogress event handler
											xhr.upload.onprogress = function(
													event) {
												var perc = Math
														.round((event.loaded / event.total) * 100);
												// $('#progressBar').text(perc +
												// '%');
												// $('#progressBar').css('width',perc
												// + '%');
											};
											return xhr;
										},
										beforeSend : function(xhr) {
											//Reset alert message and progress bar
											//		    		$('#alertMsg').text('');
											//		    		$('#progressBar').text('');
											//		    		$('#progressBar').css('width','0%');
										}
									});

							// Called on success of file upload
							ajaxReq.done(function(data) {
								//		      $('#alertMsg').text(msg);
								//		      $('input[type=file]').val('');
								//		      $('button[type=submit]').prop('disabled',false);
								var obj = JSON.parse(data);
								console.log(obj);
								$(event).closest("div").find(
										".hidden_image_name").val(
										obj.image_name);
							});

							// Called on failure of file upload
							ajaxReq.fail(function(jqXHR) {
								//		      $('#alertMsg').text(jqXHR.responseText+'('+jqXHR.status+
								//		      		' - '+jqXHR.statusText+')');
								//		      $('button[type=submit]').prop('disabled',false);
								console.log(jqXHR);
							});

							$("#candidates_detail_submit").prop('disabled',false);
						}

					}

				});