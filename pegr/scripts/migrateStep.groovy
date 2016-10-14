package pegr;
import groovy.json.*
    
def map = [
    // paired end
    "input1":"input_dataset_r1_output_stats",
    "input2":"input_dataset_r2_output_stats",
    "ad583fa93b5fed87":"fastqc_output_stats",
    "96dd6dd693e9a033":"fastqc_output_stats2",
    "71175efa22558b3b":"mark_duplicates_bam_output_stats",
    "b868e18d758bba8a":"samtool_filter2_output_stats",
    "89b5528676fe3fae":"bam_to_scidx_output_stats",
    "6f4fa8324bf2747b":"pe_histogram_output_stats",
    "b24b58817eeee721":"genetrack_output_stats",
    "072cb5142f83033f":"bedtools_intersectbed_output_stats",
    "f6186cdbed51a4cb":"cwpair2_output_stats",
    "d9078eb5a65df1e4":"extract_genomic_dna_output_stats",
    "c36cbb3fa155d5b4":"repeatmasker_wrapper_output_stats",
    "cdb3593ee534882e":"extract_genomic_dna_output_stats2",
    "96761ccd61321ce3":"repeatmasker_wrapper_output_stats2",
    "34caead642390a27":"meme_meme_output_stats",
    "7c5ed654bd70e4b5":"meme_fimo_output_stats",
    "59e21c1ec43960de":"extract_genomic_dna_output_stats3",    "cce41d830f1a2319":"fasta_nucleotide_color_plot_output_stats",
    "10a14f60d05bf206":"tag_pileup_frequency_output_stats",
    // single read
    "a442a2977eaf6790":"fastqc_output_stats",
    "09a7f17155ea255b":"bwa_mem_output_stats_single",
    "01fdf28219732417":"samtool_filter2_output_stats",
    "9c615ca963843792":"bam_to_scidx_output_stats",
    "11cc942884974181":"genetrack_output_stats",
    "14e1cfabec0d4785":"bedtools_intersectbed_output_stats",
    "118f10dfbad199cf":"cwpair2_output_stats",
    "9c95c017384e3e84":"extract_genomic_dna_output_stats",
    "0aa12b4dd1c7377c":"extract_genomic_dna_output_stats2",
    "e8d04945daa8a74d":"repeatmasker_wrapper_output_stats",
    "bedb169f660c8511":"repeatmasker_wrapper_output_stats2",
    "7451906ed56a8bcc":"meme_meme_output_stats",
    "c292a291ca2f0a8e":"meme_fimo_output_stats",
    "7dd23a78dc907d9a":"extract_genomic_dna_output_stats3",
    "3c59f1a4d3979ee8":"fasta_nucleotide_color_plot_output_stats",
    "04d9832bb2cf7335":"tag_pileup_frequency_output_stats"
]

Analysis.list().each { analysis ->
    analysis.step = map[analysis.stepId]
    analysis.save()
}
