// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;

public class BlobDiff {
    public List<String> Location;
    public DiffStatusEnumDiffStatus Status;
    public DatasetDiff Dataset;
    public EnvironmentDiff Environment;
    public CodeDiff Code;
    public ConfigDiff Config;

    public BlobDiff() {
        this.Location = null;
        this.Status = null;
        this.Dataset = null;
        this.Environment = null;
        this.Code = null;
        this.Config = null;
    }

    public BlobDiff setLocation(List<String> value) {
        this.Location = value;
        return this;
    }
    public BlobDiff setStatus(DiffStatusEnumDiffStatus value) {
        this.Status = value;
        return this;
    }
    public BlobDiff setDataset(DatasetDiff value) {
        this.Dataset = value;
        return this;
    }
    public BlobDiff setEnvironment(EnvironmentDiff value) {
        this.Environment = value;
        return this;
    }
    public BlobDiff setCode(CodeDiff value) {
        this.Code = value;
        return this;
    }
    public BlobDiff setConfig(ConfigDiff value) {
        this.Config = value;
        return this;
    }

    static public BlobDiff fromProto(ai.verta.modeldb.versioning.BlobDiff blob) {
        if (blob == null) {
            return null;
        }

        BlobDiff obj = new BlobDiff();
        {
            Function<ai.verta.modeldb.versioning.BlobDiff,List<String>> f = x -> blob.getLocationList();
            obj.Location = f.apply(blob);
        }
        {
            Function<ai.verta.modeldb.versioning.BlobDiff,DiffStatusEnumDiffStatus> f = x -> DiffStatusEnumDiffStatus.fromProto(blob.getStatus());
            obj.Status = f.apply(blob);
        }
        {
            Function<ai.verta.modeldb.versioning.BlobDiff,DatasetDiff> f = x -> DatasetDiff.fromProto(blob.getDataset());
            obj.Dataset = f.apply(blob);
        }
        {
            Function<ai.verta.modeldb.versioning.BlobDiff,EnvironmentDiff> f = x -> EnvironmentDiff.fromProto(blob.getEnvironment());
            obj.Environment = f.apply(blob);
        }
        {
            Function<ai.verta.modeldb.versioning.BlobDiff,CodeDiff> f = x -> CodeDiff.fromProto(blob.getCode());
            obj.Code = f.apply(blob);
        }
        {
            Function<ai.verta.modeldb.versioning.BlobDiff,ConfigDiff> f = x -> ConfigDiff.fromProto(blob.getConfig());
            obj.Config = f.apply(blob);
        }
        return obj;
    }

    public ai.verta.modeldb.versioning.BlobDiff.Builder toProto() {
        ai.verta.modeldb.versioning.BlobDiff.Builder builder = ai.verta.modeldb.versioning.BlobDiff.newBuilder();
        {
            if (this.Location != null) {
                Function<ai.verta.modeldb.versioning.BlobDiff.Builder,Void> f = x -> { builder.addAllLocation(this.Location); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.Status != null) {
                Function<ai.verta.modeldb.versioning.BlobDiff.Builder,Void> f = x -> { builder.setStatus(this.Status.toProto()); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.Dataset != null) {
                Function<ai.verta.modeldb.versioning.BlobDiff.Builder,Void> f = x -> { builder.setDataset(this.Dataset.toProto()); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.Environment != null) {
                Function<ai.verta.modeldb.versioning.BlobDiff.Builder,Void> f = x -> { builder.setEnvironment(this.Environment.toProto()); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.Code != null) {
                Function<ai.verta.modeldb.versioning.BlobDiff.Builder,Void> f = x -> { builder.setCode(this.Code.toProto()); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.Config != null) {
                Function<ai.verta.modeldb.versioning.BlobDiff.Builder,Void> f = x -> { builder.setConfig(this.Config.toProto()); return null; };
                f.apply(builder);
            }
        }
        return builder;
    }

    public void preVisitShallow(Visitor visitor) throws ModelDBException {
        visitor.preVisitBlobDiff(this);
    }

    public void preVisitDeep(Visitor visitor) throws ModelDBException {
        this.preVisitShallow(visitor);
        visitor.preVisitDeepListOfString(this.Location);
        visitor.preVisitDeepDiffStatusEnumDiffStatus(this.Status);
        visitor.preVisitDeepDatasetDiff(this.Dataset);
        visitor.preVisitDeepEnvironmentDiff(this.Environment);
        visitor.preVisitDeepCodeDiff(this.Code);
        visitor.preVisitDeepConfigDiff(this.Config);
    }

    public BlobDiff postVisitShallow(Visitor visitor) throws ModelDBException {
        return visitor.postVisitBlobDiff(this);
    }

    public BlobDiff postVisitDeep(Visitor visitor) throws ModelDBException {
        this.Location = visitor.postVisitDeepListOfString(this.Location);
        this.Status = visitor.postVisitDeepDiffStatusEnumDiffStatus(this.Status);
        this.Dataset = visitor.postVisitDeepDatasetDiff(this.Dataset);
        this.Environment = visitor.postVisitDeepEnvironmentDiff(this.Environment);
        this.Code = visitor.postVisitDeepCodeDiff(this.Code);
        this.Config = visitor.postVisitDeepConfigDiff(this.Config);
        return this.postVisitShallow(visitor);
    }
}
