<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form_container">
	<div class="container_header">
		<span>Uploaded Documents</span>
		<button id="uploaded_documents" type="button"
			class="btn-expand-collapse form_collapse"
			onclick="toggleContent(this)"></button>
	</div>
	<div class="form_content">
		<div class="upload_document">
			<div class="input_hint">
				<input type="text" placeholder="Browse..." id="upload_document"
					name="upload_btn" />
				<p>allowed format: jpg, pdf, doc / max file size 2MB</p>
			</div>
			<div id="docfileuploader">Upload</div>
			<div class="clearDiv"></div>
			<div class="uploadedFiles">
				<c:if test="${ not empty headOfficeDetailForm.uploadedDocuments }">
					<p>Uploaded Documents</p>
						<table>
							<tbody>
								<c:forEach items="${headOfficeDetailForm.uploadedDocuments }" var="docfile">
									<tr>
										<td class="fileName">Documents File</td>
										<td class="filePath">
											<a target="_blank" href="${docfile.url}">${docfile.altText}</a>
											<input type="hidden" name="docfile.code" value="${docfile.code}">
										</td>
										<td class="fileAction"><button type="button">Delete</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</c:if>
			</div>	
		</div>
	</div>
</div>