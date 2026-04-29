# PEGR Database Schema

ER diagrams for each functional area. Open any file in VS Code (with Mermaid Preview) or paste into [mermaid.live](https://mermaid.live).

| File | Area | Key Models |
|------|------|------------|
| [01_user_auth.md](01_user_auth.md) | User & Authentication | User, Role, RoleGroup, UserRole, UserRoleGroup, RoleGroupRole, Token |
| [02_organization.md](02_organization.md) | Organization | Organization, Lab, Company, Address, Funding, Invoice |
| [03_biological_sources.md](03_biological_sources.md) | Biological Sources | Species, Strain, CellSource, CellSourceBatch, Tissue, Histology, Sex, GrowthMedia, CellSourceTreatment, Inventory |
| [04_target_antibody.md](04_target_antibody.md) | Target & Antibody | Target, TargetType, Antibody, AbHost, IgType |
| [05_sample_project.md](05_sample_project.md) | Sample & Project | Sample, Project, Assay, ReplicateSet, ControlSample, PoolSamples, ProjectSamples, ProjectUser, ProjectFunding |
| [06_protocol_workflow.md](06_protocol_workflow.md) | Protocol & Lab Workflow | Protocol, ProtocolGroup, ProtocolInstance, ProtocolInstanceBag, Item, ItemType, ItemTypeCategory, ItemAntibody, ItemSequenceIndices |
| [07_sequencing.md](07_sequencing.md) | Sequencing | SequenceRun, SequencingExperiment, SequencingCohort, SampleInRun, SequenceIndex, RunStats, ReadType, SequencingPlatform |
| [08_genomics_analysis.md](08_genomics_analysis.md) | Genomics & Analysis | Genome, Chromosome, ChromSequence, SequenceAlignment, Analysis, Pipeline, Aligner, AlignType, SummaryReport, ReferenceFeature |
| [09_reference_dictionary.md](09_reference_dictionary.md) | Reference & Dictionary | Definition, History, Chores |
