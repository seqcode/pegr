# Mission

## What PEGR is

PEGR (Platform for Epigenomic and Genomic Research) is a web platform that records
metadata for biological samples and sequencing experiments, drives downstream data
processing workflows, and reports results back to the people who ran the experiment.

It is the connective tissue between the parts of a genomics lab that would otherwise
live in spreadsheets and lab notebooks: people, samples, protocols, sequencers, and
bioinformatics computation.

Reference: Shao D, Kellogg G, Mahony S, Lai W, Pugh BF. *PEGR: a management platform
for ChIP-based next generation sequencing pipelines.* doi:10.1101/2021.07.26.453821

## Why it exists

Wet-lab work in a ChIP-exo / ChIP-seq lab generates a long chain of provenance:
a cell source is grown and treated, a batch is split into samples, each sample runs
through a series of protocols, gets an antibody and a sequencing index, is pooled into
a sequencing run, and finally comes back as alignment statistics and QC figures.

Without a system of record, that chain lives in disconnected files and only one person
knows how any given result was produced. PEGR makes the chain queryable, auditable, and
shareable, and it makes the transition from bench to pipeline automatic rather than manual.

## Who it serves

| User | What they need from PEGR |
|------|--------------------------|
| Bench scientist | Log samples, run protocol instances over a bag of items, track inventory |
| Lab manager / admin | Manage users, protocols, item types, funding, invoices, reference data |
| Sequencing core | Register sequencing runs, assign indices, track run status |
| Bioinformatician | Pull sample metadata over the API, push alignment stats and analysis results back |
| PI / collaborator | See project-level reports and QC without needing lab-internal access |

## What it does

- **Sample provenance** — cell source, batch, treatment, antibody, protocol history, and
  replicate structure for every sample.
- **Protocol execution** — protocols are first-class records; a *protocol instance* applies
  one to a bag of items and records who did it, when, and with what.
- **Sequencing management** — sequencing runs, indices, pooling, run status, read types.
- **Pipeline integration** — a REST API (`ApiController`) that external pipelines use to
  fetch sample metadata and post back alignment statistics, analyses, and dataset links.
- **Reporting** — per-project and per-run reports, QC figures, exports to Excel, GEO
  accession tracking.
- **Access control** — role-based (`ROLE_ADMIN` / `ROLE_MEMBER` / `ROLE_USER`) with
  per-project membership, plus optional institutional SSO.
