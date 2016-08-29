/* Generated by Together */

package uff.ic.tcc00175.geocat;

import uff.ic.tcc00175.catfwk.model.DatasetDescription;
import uff.ic.tcc00175.catfwk.model.DictionaryDescBuilder;
import uff.ic.tcc00175.catfwk.model.InvalidDatasetDescException;

/**
 * @author Luiz Andr�
 * @version 1.0
 * @since 1.0
 * @alias ADLFeaturesBuilder*/
public class ISO19115ADLFeaturesBuilder extends DictionaryDescBuilder {
	/**
	 * @supplierCardinality 0..1*/
	private OOISO19115GeoImage imageDescription = null;

	/**
	 * @return Returns the image.
	 */
	private OOISO19115GeoImage getImage() {
		if (imageDescription == null)
			imageDescription = new OOISO19115GeoImage();
		return imageDescription;
	}

	/**
	 * Constr�i estrutura de calaloga��o das features extra�das do gazetteer
	 * ADL.
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @param metadataElements
	 *            vetor de chaves XML que representam a cole��o de features
	 *            extra�das do gazetteer
	 */
	public void build(Object metadataElements)
			throws InvalidDatasetDescException, NumberFormatException {
		double x0 = 0, y0 = 0, x1 = 0, y1 = 0;
		boolean fail = true;
		boolean fIdent = false, fName = false, fX0 = false, fY0 = false, fX1 = false, fY1 = false;
		imageDescription = null;
		String identifier = null;
		String displayName = null;
		MetadataElement[] elements = (MetadataElement[]) metadataElements;
		int end = 0;
		if (elements != null)
			end = elements.length;

		/*
		 * Varre o vetor de chaves XML obtidos como resposta do gazetteer ADL e
		 * const�i estrutura de cataloga��o
		 * 
		 * Se houver resposta v�lida do gazetteer, estar� contida em uma chave
		 * XML <query-response>. Se a resposta n�o possuir essa chave uma
		 * exce��o InvalidSourceContentException dever� ser lan�ada indicando
		 * erro na consulta.
		 * 
		 * Cada feature retornada pela consulta vir� em uma chave XML
		 * <gazetteer-standard-report>. O Loop reinicializa todas as vari�veis a
		 * cada vez que essa chave � encontrada indicando que nova feature
		 * dever� ser montada.
		 * 
		 * Entre uma chave <gazetteer-standard-report> e outra s�o procuradas
		 * chaves com metadados da feature que s�o: <identifier>,
		 * <diaplay-name>, <gml:X> e <gml:Y>. Ap�s a identifica��o de todos
		 * esses metadados uma nova feature � adicionada � estrutura de
		 * cataloga��o e os valores descoberto acrescentados.
		 * 
		 */
		for (int i = 0; i < end; i++) {
			if (elements[i] != null
					&& elements[i].getQname().equals("query-response"))
				fail = false;
			if (elements[i] != null
					&& elements[i].getQname().equals(
							"gazetteer-standard-report")) {
				fIdent = false;
				fName = false;
				fX0 = false;
				fY0 = false;
				fX1 = false;
				fY1 = false;
			} else if (elements[i] != null
					&& elements[i].getQname().equals("identifier")) {
				identifier = elements[i].getValue();
				fIdent = true;
			} else if (elements[i] != null
					&& elements[i].getQname().equals("display-name")) {
				displayName = elements[i].getValue();
				fName = true;
			} else if (elements[i] != null
					&& elements[i].getQname().equals("gml:X")
					&& elements[i].getValue() != null && !fX0) {
				x0 = Double.valueOf(elements[i].getValue()).doubleValue();
				fX0 = true;
			} else if (elements[i] != null
					&& elements[i].getQname().equals("gml:Y")
					&& elements[i].getValue() != null && !fY0) {
				y0 = Double.valueOf(elements[i].getValue()).doubleValue();
				fY0 = true;
			} else if (elements[i] != null
					&& elements[i].getQname().equals("gml:X")
					&& elements[i].getValue() != null && !fX1 && fX0) {
				x1 = Double.valueOf(elements[i].getValue()).doubleValue();
				fX1 = true;
			} else if (elements[i] != null
					&& elements[i].getQname().equals("gml:Y")
					&& elements[i].getValue() != null && !fY1 && fY0) {
				y1 = Double.valueOf(elements[i].getValue()).doubleValue();
				fY1 = true;
			}
			/*
			 * Se todos os metadados da feature foram descobertos na varredura
			 * do vetor de chaves, ent�o acrescenta nova feature e seus
			 * metadados.
			 */
			if (fIdent && fName && fX0 && fY0 && fX1 && fY1) {
				if (getImage().getIdentifier() != null)
					/*
					 * adiciona nova feature
					 */
					getImage().incrementDatasetCapacity();
				/*
				 * registra na nova feature os valores obtidos
				 */
				getImage().setIdentifier(identifier);
				getImage().setLeftUpperCornerPoint(x0, y0);
				getImage().setRightLowerCornerPoint(x1, y1);
				getImage().setName(displayName);
				fIdent = false;
				fName = false;
				fX0 = false;
				fY0 = false;
				fX1 = false;
				fY1 = false;
			}
		}
		if (fail)
			throw new InvalidDatasetDescException(
					"Building InformationResource: invalid source content");
	}

	/**
	 * @version 1.0
	 * @since 1.0
	 * @return resource constru�do com base no vetor de chaves XML informados no
	 *         m�todo de constru��o
	 */
	public DatasetDescription getDatasetDescription() {
		return imageDescription;
	}
}