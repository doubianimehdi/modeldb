import { UnregisterCallback } from 'history';
import * as React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps, Router, withRouter } from 'react-router-dom';
import ModelRecord from '../../models/ModelRecord';
import Project from '../../models/Project';
import { IApplicationState, IConnectedReduxProps } from '../../store/store';
import styles from './Breadcrumb.module.css';
import { BreadcrumbItem } from './BreadcrumbItem';
import headerArrow from './images/header-arrow.svg';

interface IPropsFromState {
  experimentRuns?: ModelRecord[] | null;
  modelRecord?: ModelRecord | null;
}

interface ILocalState {
  url: string;
}

type AllProps = IPropsFromState & IConnectedReduxProps & RouteComponentProps;

class Breadcrumb extends React.Component<AllProps, ILocalState> {
  private unlistenCallback: UnregisterCallback | undefined = undefined;

  private indexBreadcrumbItem = new BreadcrumbItem(/^\/$/, '/', 'PROJECTS');
  private projectBreadcrumbItem = new BreadcrumbItem(/^\/project\/([\w-]*)\/exp-runs.?$/);
  private modelBreadcrumbItem = new BreadcrumbItem(/^\/project\/([\w-]*)\/exp-run\/([\w-]*).?$/);

  public constructor(props: AllProps) {
    super(props);

    this.state = {
      url: this.props.history.location.pathname.toLowerCase()
    };

    this.prepareItem = this.prepareItem.bind(this);
  }

  public componentDidMount() {
    this.unlistenCallback = this.props.history.listen((location, action) => {
      this.setState({ ...this.state, url: location.pathname.toLowerCase() });
    });
  }

  public componentWillUnmount() {
    if (this.unlistenCallback) {
      this.unlistenCallback();
    }
  }

  public render() {
    const content: JSX.Element[] = [];
    let currentItem = this.prepareItem();
    if (!currentItem) return '';
    content.push(this.renderItem(currentItem, true));

    while (currentItem.PreviousItem) {
      currentItem = currentItem.PreviousItem;
      content.push(this.renderItem(currentItem));
    }

    return (
      <div className={styles.content}>
        {content.reverse().map((value: JSX.Element, index: number) => {
          return <span key={index}>{value}</span>;
        })}
      </div>
    );
  }

  private prepareItem(): BreadcrumbItem | undefined {
    const { experimentRuns, modelRecord } = this.props;
    // project name no-longer exist @ModelRecord, this could be resolved either by composing an object for breadcrumbs while
    // subscribing to "projects" array at store to fetch associated project name if needed
    // this.projectBreadcrumbItem.Name = project ? project.Name.toLocaleUpperCase() : model ? model.ProjectName.toLocaleUpperCase() : '';

    this.projectBreadcrumbItem.Name = 'PROJECT';
    this.projectBreadcrumbItem.Path = `/project/${
      experimentRuns && experimentRuns.length > 0 ? experimentRuns[0].ProjectId : modelRecord ? modelRecord.ProjectId : ''
    }/exp-runs`;
    this.projectBreadcrumbItem.PreviousItem = this.indexBreadcrumbItem;

    this.modelBreadcrumbItem.Name = modelRecord ? modelRecord.Name.toLocaleUpperCase() : '';
    this.modelBreadcrumbItem.Path = modelRecord ? `/project/${modelRecord.ProjectId}/exp-run/${modelRecord.Id}` : '';
    this.modelBreadcrumbItem.PreviousItem = this.projectBreadcrumbItem;

    const breadcrumbItems: BreadcrumbItem[] = [];
    breadcrumbItems.push(this.indexBreadcrumbItem);
    breadcrumbItems.push(this.projectBreadcrumbItem);
    breadcrumbItems.push(this.modelBreadcrumbItem);

    return breadcrumbItems.find(x => x.ShouldMatch.test(this.state.url));
  }

  private renderItem(item: BreadcrumbItem, active: boolean = false) {
    return (
      <span>
        {item.PreviousItem ? <img className={styles.arrow} src={headerArrow} /> : ''}
        <Link className={`${styles.link} ${active ? styles.active_link : ''}`} to={item.Path}>
          {item.Name}
        </Link>
      </span>
    );
  }
}

const mapStateToProps = ({ experimentRuns, modelRecord }: IApplicationState) => ({
  experimentRuns: experimentRuns.data,
  modelRecord: modelRecord.data
});

export default withRouter(connect(mapStateToProps)(Breadcrumb));